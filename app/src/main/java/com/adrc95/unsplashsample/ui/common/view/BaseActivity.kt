package com.adrc95.unsplashsample.ui.common.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<VBinding : ViewBinding> : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected val binding : VBinding by lazy { bindView(layoutInflater) }

    abstract fun bindView(layoutInflater: LayoutInflater): VBinding

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onActivityCreated()
    }

    open fun onActivityCreated() {}

    open fun initFlows() {}

    override fun onStart() {
        super.onStart()
        job = SupervisorJob()
        initFlows()
    }

    override fun onStop() {
        job.cancel()
        super.onStop()
    }

}