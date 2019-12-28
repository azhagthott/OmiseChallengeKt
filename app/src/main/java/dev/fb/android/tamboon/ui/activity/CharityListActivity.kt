package dev.fb.android.tamboon.ui.charity.activity

import dev.fb.android.tamboon.R
import dev.fb.android.tamboon.base.BaseActivity
import dev.fb.android.tamboon.databinding.ActivityCharityListBinding
import dev.fb.android.tamboon.viewmodel.MainActivityViewModel

class CharityListActivity : BaseActivity<ActivityCharityListBinding>() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun injectDependency() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_charity_list
    }

    override fun initView() {

    }
}
