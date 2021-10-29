package com.adrc95.unsplashsample.ui.common.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.adrc95.unsplashsample.ui.common.presenter.BasePresenter

abstract class BaseActivity<VBinding : ViewBinding, V : BaseView,P : BasePresenter<V>> : AppCompatActivity(),
    BaseView {

    protected val binding : VBinding by lazy { bindView(layoutInflater) }

    protected abstract val presenter: P

    abstract fun bindView(layoutInflater: LayoutInflater): VBinding

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.attachView(this as V)
        presenter.initialize(intent.extras)
        onActivityCreated()
    }

    open fun onActivityCreated() {}

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

}