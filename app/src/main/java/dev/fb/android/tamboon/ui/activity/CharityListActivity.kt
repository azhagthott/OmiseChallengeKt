package dev.fb.android.tamboon.ui.activity

import android.omise.core.ui.BaseActivity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.fb.android.tamboon.R
import dev.fb.android.tamboon.databinding.ActivityCharityListBinding
import dev.fb.android.tamboon.ui.adapter.CharityListAdapter
import dev.fb.android.tamboon.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CharityListActivity : BaseActivity<ActivityCharityListBinding>() {

    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var adapter: CharityListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_charity_list
    }

    override fun initView() {
        initViewModel()
        adapter = CharityListAdapter(this)
        binder?.lvCharities?.adapter = adapter
    }

    override fun initViewModel() {
        viewModel.charityList.observe(this, Observer { newCharityList ->
            adapter.updateData(newCharityList)
        })

        viewModel.showLoading.observe(this, Observer { showLoading ->
            binder?.progressCircular?.visibility = if (showLoading!!) View.VISIBLE else View.GONE
        })

        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this, showError, Toast.LENGTH_SHORT).show()
        })
    }
}
