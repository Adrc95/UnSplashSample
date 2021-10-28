package com.adrc95.unsplashsample.ui.detail

import android.os.Bundle
import arrow.core.Either
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.domain.exception.ApiError
import com.adrc95.unsplashsample.ui.common.presenter.BasePresenter
import com.adrc95.unsplashsample.ui.common.view.BaseView
import com.adrc95.usecase.GetPhoto
import com.adrc95.usecase.Invoker

class DetailPresenter(
  private val invoker: Invoker,
  private val getPhoto: GetPhoto
) : BasePresenter<DetailPresenter.View>() {

  private lateinit var photoId: String

  override fun initialize(extras: Bundle?) {
    photoId = extras?.getString(DetailActivity.PHOTO) ?: ""
    loadPhoto()
  }

  private fun loadPhoto() {
    getView()?.showLoading(true)
    val params = GetPhoto.Params(photoId)
    invoker.execute(getPhoto, params, ::onPhotoArrived)
  }

  private fun onPhotoArrived(result: Either<ApiError, Photo>) {
    result.fold(ifLeft = {
      getView()?.showLoading(false)
      getView()?.displayLoadingPhotoError()
    }, ifRight = {
      getView()?.showLoading(false)
      getView()?.renderPhoto(it)
    })
  }

  fun onCameraTextClick(camera: Camera?) {
    camera?.let {
      getView()?.showCameraInfo(camera)
    }
  }

  fun onBackClick() {
    getView()?.goBack()
  }

  interface View : BaseView {
    fun showCameraInfo(camera: Camera)
    fun renderPhoto(photo: Photo)
    fun displayLoadingPhotoError()
    fun showLoading(show: Boolean)
    fun goBack();
  }
}