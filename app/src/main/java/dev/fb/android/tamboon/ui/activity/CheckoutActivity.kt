package dev.fb.android.tamboon.ui.activity

import android.content.Context
import android.content.Intent
import android.omise.charity.domain.model.Charity
import android.omise.charity.domain.model.Donation
import android.omise.core.ui.activity.BaseActivity
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import co.omise.android.api.Client
import co.omise.android.api.RequestListener
import co.omise.android.models.Capability
import co.omise.android.models.Token
import co.omise.android.ui.OmiseActivity
import co.omise.android.ui.OmiseActivity.Companion.EXTRA_TOKEN_OBJECT
import co.omise.android.ui.PaymentCreatorActivity
import com.bumptech.glide.Glide
import dev.fb.android.tamboon.BuildConfig
import dev.fb.android.tamboon.R
import dev.fb.android.tamboon.databinding.ActivityCheckoutBinding
import dev.fb.android.tamboon.viewmodel.CheckoutActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CheckoutActivity : BaseActivity<ActivityCheckoutBinding>(), TextWatcher {

    private val viewModel: CheckoutActivityViewModel by viewModel()

    private val TAG = CheckoutActivity::class.java.canonicalName
    private val client = Client(BuildConfig.OMISE_PKEY)
    private val REQUEST_CC: Int = 1001

    private lateinit var capability: Capability
    private lateinit var donation: Donation
    private var amount = 0

    val request = Capability.GetCapabilitiesRequestBuilder().build()

    companion object {

        const val CHARITY_NAME = "CHARITY_NAME"
        const val CHARITY_URL = "CHARITY_URL"

        fun newIntent(context: Context, charity: Charity): Intent {
            val intent = Intent(context, CheckoutActivity::class.java)
            intent.putExtra(CHARITY_NAME, charity.charityName)
            intent.putExtra(CHARITY_URL, charity.charityLogoUrl)
            return intent
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_checkout
    }

    override fun initView() {

        setSupportActionBar(binder.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        requestCapability()

        setUpUI()

        binder.btnDonateNow.setOnClickListener { showPaymentCreatorActivity() }
    }

    override fun initViewModel() {

        viewModel.checkoutSuccess.observe(this, Observer {
            Toast.makeText(this, R.string.checkout_success, Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                startActivity(Intent(this, CharityListActivity::class.java))
                finish()
            }, 1000)
        })

        viewModel.showError.observe(this, Observer { showError ->
            Log.e(TAG, "showError: " + showError)
        })

        viewModel.showLoading.observe(this, Observer { showLoading ->
            binder.progressCircular.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })
    }

    private fun showPaymentCreatorActivity() {
        val intent = Intent(this@CheckoutActivity, PaymentCreatorActivity::class.java)
        intent.putExtra(OmiseActivity.EXTRA_PKEY, BuildConfig.OMISE_PKEY)
        intent.putExtra(OmiseActivity.EXTRA_AMOUNT, amount)
        intent.putExtra(OmiseActivity.EXTRA_CURRENCY, "thb")
        intent.putExtra(OmiseActivity.EXTRA_CAPABILITY, capability)
        startActivityForResult(intent, REQUEST_CC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.donation_canceled, Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == REQUEST_CC) {

            binder.btnDonateNow.isEnabled = false

            val token = data?.getParcelableExtra<Token>(EXTRA_TOKEN_OBJECT)
            Log.i(TAG, "token: " + token)
            initViewModel()

            val donationToken = token!!.id.toString()
            val donationName = token.card!!.name.toString()

            donation = Donation(
                donationName,
                donationToken,
                amount
            )
            viewModel.makeCheckout(donation)
        }
    }

    private fun setUpUI() {
        try {

            val name = intent.extras!!.getString(CHARITY_NAME)
            val url = intent.extras!!.getString(CHARITY_URL)

            binder.tvCharityName.text = name
            Glide.with(this).load(url).into(binder.ivCharityLogo)

            binder.etAmount.addTextChangedListener(this)

        } catch (ex: Exception) {
            Log.e(TAG, "Exception: " + ex.message)
        }
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(c: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (c.toString().isNotEmpty()) {
            binder.btnDonateNow.isEnabled = c.toString().toLong() in 20..1000000
            if (c.toString().toLong() > 100000) {
                binder.btnDonateNow.isEnabled = false
                Toast.makeText(this, R.string.exceed_maximum, Toast.LENGTH_SHORT).show()
            } else {
                amount = binder.etAmount.text.toString().toInt()
            }
        } else {
            binder.btnDonateNow.isEnabled = false
        }
    }

    private fun requestCapability() {
        client.send(request, object : RequestListener<Capability> {
            override fun onRequestSucceed(model: Capability) {
                capability = model
            }

            override fun onRequestFailed(throwable: Throwable) {
                Log.e(TAG, "Throwable: " + throwable.message)
            }
        })

    }
}