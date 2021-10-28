package com.adrc95.unsplashsample.ui.common.presenter

import com.adrc95.unsplashsample.ui.common.view.BaseView

interface Presenter<V : BaseView> {
    fun getView(): V?
    fun attachView(view: V)
    fun detachView()
  }