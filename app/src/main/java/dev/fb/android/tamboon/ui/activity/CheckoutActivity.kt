package dev.fb.android.tamboon.ui.activity

import android.content.Context
import android.content.Intent
import android.omise.charity.domain.model.Charity
import android.omise.core.ui.BaseActivity
import android.util.Log
import android.widget.Toast
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

class CheckoutActivity : BaseActivity<ActivityCheckoutBinding>() {

    private val TAG = CheckoutActivity::class.java.canonicalName
    private val client = Client(BuildConfig.OMISE_PKEY)
    private val REQUEST_CC: Int = 1001

    private lateinit var capability: Capability

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

        val name = intent.extras!!.getString(CHARITY_NAME)
        val url = intent.extras!!.getString(CHARITY_URL)

        binder?.tvCharityName?.text = name
        Glide.with(this)
            .load(url)
            .into(binder?.ivCharityLogo!!)

        binder!!.btnDonateNow.setOnClickListener {
            showPaymentCreatorActivity()
        }

        client.send(request, object : RequestListener<Capability> {
            override fun onRequestSucceed(model: Capability) {
                capability = model
            }

            override fun onRequestFailed(throwable: Throwable) {
                Log.e(TAG, "Throwable: " + throwable.message)
            }
        })
    }

    override fun initViewModel() {
    }

    private fun showPaymentCreatorActivity() {
        val intent = Intent(this@CheckoutActivity, PaymentCreatorActivity::class.java)
        intent.putExtra(OmiseActivity.EXTRA_PKEY, BuildConfig.OMISE_PKEY)
        intent.putExtra(OmiseActivity.EXTRA_AMOUNT, 150000L)
        intent.putExtra(OmiseActivity.EXTRA_CURRENCY, "thb")
        intent.putExtra(OmiseActivity.EXTRA_CAPABILITY, capability)
        startActivityForResult(intent, REQUEST_CC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == REQUEST_CC) {
            val token = data?.getParcelableExtra<Token>(EXTRA_TOKEN_OBJECT)
            Toast.makeText(this, "totken: " + token, Toast.LENGTH_SHORT).show()
        }
    }
}