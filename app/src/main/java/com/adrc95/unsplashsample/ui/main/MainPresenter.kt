package com.adrc95.unsplashsample.ui.main

import android.os.Bundle
import arrow.core.Either
import com.adrc95.domain.model.Photo
import com.adrc95.domain.exception.ApiError
import com.adrc95.unsplashsample.ui.common.navigator.Navigator
import com.adrc95.unsplashsample.ui.common.presenter.BasePresenter
import com.adrc95.unsplashsample.ui.common.view.BaseView
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker

class MainPresenter (private val navigator: Navigator, private val invoker: Invoker,
                    private val getPhotos: GetPhotos) : BasePresenter<MainPresenter.View>() {

    override fun initialize(extras: Bundle?) {
        loadPhotos()
    }

    private fun loadPhotos(page: Int = 1) {
        getView()?.showLoading()
        val params = GetPhotos.Params(page = page)
        invoker.execute(getPhotos, params, ::onPhotosArrived)
    }

    private fun onPhotosArrived(result: Either<ApiError, List<Photo>>) {
        result.fold(ifLeft = {
            getView()?.hideLoading()
        }, ifRight = {
            getView()?.hideLoading()
            getView()?.renderPhotos(it)
        })
    }

    fun onLoadMore(page: Int) {
        loadPhotos(page)
    }

    fun onPhotoClicked(photo : Photo) {
        navigator.openDetail(photo)
    }

    interface View : BaseView {
        fun renderPhotos(photos: List<Photo>)
    }
}
