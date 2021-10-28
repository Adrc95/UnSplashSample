package com.adrc95.unsplashsample.ui.common.presenter

import android.os.Bundle
import com.adrc95.unsplashsample.ui.common.view.BaseView
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> : Presenter<V> {

  private var viewRef: WeakReference<V>? = null

  override fun getView(): V? = viewRef?.get()

  override fun attachView(view: V) {
    viewRef = WeakReference(view)
  }

  override fun detachView() {
    viewRef?.clear()
    viewRef = null
  }

  open fun initialize(extras: Bundle?) {}

}