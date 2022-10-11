package com.adrc95.unsplashsample.ui.common.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VBinding : ViewBinding> : AppCompatActivity() {

    protected val binding : VBinding by lazy { bindView(layoutInflater) }

    abstract fun bindView(layoutInflater: LayoutInflater): VBinding

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onActivityCreated()
    }

    open fun onActivityCreated() {}

}