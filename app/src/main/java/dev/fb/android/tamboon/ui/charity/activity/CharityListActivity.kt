package dev.fb.android.tamboon.ui.charity.activity

import android.annotation.SuppressLint
import android.util.Log
import dev.fb.android.tamboon.R
import dev.fb.android.tamboon.base.BaseActivity
import dev.fb.android.tamboon.data.entity.CharityEntity
import dev.fb.android.tamboon.data.remote.ApiService
import dev.fb.android.tamboon.databinding.ActivityCharityListBinding
import dev.fb.android.tamboon.domain.model.Charity
import dev.fb.android.tamboon.ui.adapter.CharityListAdapter
import dev.fb.android.tamboon.util.Utility
import dev.fb.android.tamboon.viewmodel.MainActivityViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharityListActivity : BaseActivity<ActivityCharityListBinding>() {

    override fun injectDependency() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_charity_list
    }

    override fun initView() {

        var mainViewModel: MainActivityViewModel =


        val adapter = CharityListAdapter(this, mainViewModel.getCharityList())
        binder!!.lvCharities.adapter = adapter


    }

    @SuppressLint("CheckResult")
    fun call() {
        if (Utility().isConnectedToInternet(this)) {
            val observable = ApiService.charityApiCall().getCharityList()
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    for (charity: CharityEntity in it.data) {
                        Log.d("CharityListActivity", charity.name)
                    }
                }, {
                    Log.e("CharityListActivity", it.message)
                })
        }
    }

    private fun charityLis(): List<Charity> {
        return listOf(
            Charity(
                1,
                "asdsdf",
                "https://static.javatpoint.com/servletpages/servletterminology/images/http-requests.jpg"
            )
        )
    }
}
