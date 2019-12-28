package android.omise.core.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BINDER : ViewDataBinding> : AppCompatActivity() {

    var binder: BINDER? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
        initViewModel()
    }
}