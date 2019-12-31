package dev.fb.android.tamboon.ui.activity

import android.omise.charity.domain.model.Charity
import android.omise.core.ui.activity.BaseActivity
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import dev.fb.android.tamboon.R
import dev.fb.android.tamboon.databinding.ActivityCharityListBinding
import dev.fb.android.tamboon.ui.adapter.CharityListAdapter
import dev.fb.android.tamboon.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharityListActivity : BaseActivity<ActivityCharityListBinding>() {

    private val TAG: String? = CharityListActivity::class.java.canonicalName

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var adapter: CharityListAdapter
    private lateinit var list: List<Charity>

    override fun getLayoutId(): Int {
        return R.layout.activity_charity_list
    }

    override fun initView() {
        initViewModel()
        adapter = CharityListAdapter(this)
        binder.lvCharities.adapter = adapter
        binder.lvCharities.setOnItemClickListener { _, _, postion, _ ->
            run {
                val charityItemId = list[postion]
                val intent = CheckoutActivity.newIntent(this, charityItemId)
                startActivity(intent)
            }
        }
    }

    override fun initViewModel() {
        viewModel.charityList.observe(this, Observer { newCharityList ->
            this.list = newCharityList
            adapter.updateData(newCharityList)
        })

        viewModel.showLoading.observe(this, Observer { showLoading ->
            binder.progressCircular.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        viewModel.showError.observe(this, Observer { showError ->
            Log.e(TAG, "Error: " + showError)
            binder.tvError.visibility = View.VISIBLE
            binder.lvCharities.visibility = View.GONE
        })
    }
}
