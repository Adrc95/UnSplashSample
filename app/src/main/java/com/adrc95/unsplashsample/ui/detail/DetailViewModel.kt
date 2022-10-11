package com.adrc95.unsplashsample.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo
import com.adrc95.usecase.GetPhoto
import com.adrc95.usecase.Invoker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (savedStateHandle: SavedStateHandle,
  private val invoker: Invoker, private val getPhoto: GetPhoto) : ViewModel() {

  private val _state = MutableStateFlow(DetailViewState())
  val state: StateFlow<DetailViewState> = _state.asStateFlow()

  private val photoId: String by lazy {
    savedStateHandle.get<String>(DetailActivity.PHOTO) ?: ""
  }

  init {
    loadPhoto()
  }

  private fun loadPhoto() {
    _state.value = DetailViewState(loading = true)
    val params = GetPhoto.Params(photoId)
    invoker.execute(getPhoto, params, ::onPhotoArrived)
  }

  private fun onPhotoArrived(result: Either<ApiError, Photo>) {
    result.fold(ifLeft = {
      _state.value = DetailViewState(error = it)
    }, ifRight = {
      _state.value = DetailViewState(photo = it)
    })
  }

}