package com.adrc95.unsplashsample.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.ui.common.Event
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

  private val _state = MutableStateFlow<DetailViewState>(DetailViewState.LoadPhotoDetail)
  val state : StateFlow<DetailViewState> = _state.asStateFlow()

  private val _error = MutableLiveData<Event<Unit>>()
  val error : LiveData<Event<Unit>> = _error

  private val _back = MutableLiveData<Event<Unit>>()
  val back : LiveData<Event<Unit>> = _back

  private val _cameraInfo = MutableLiveData<Event<Camera>>()
  val cameraInfo : LiveData<Event<Camera>> = _cameraInfo

  private val photoId: String by lazy {
    savedStateHandle.get<String>(DetailActivity.PHOTO) ?: ""
  }

  fun onLoadPhotoDetail() {
    loadPhoto()
  }

  private fun loadPhoto() {
    _state.value = DetailViewState.Loading
    val params = GetPhoto.Params(photoId)
    invoker.execute(getPhoto, params, ::onPhotoArrived)
  }

  private fun onPhotoArrived(result: Either<ApiError, Photo>) {
    result.fold(ifLeft = {
      _error.value = Event(Unit)
    }, ifRight = {
      _state.value = DetailViewState.RenderPhoto(it)
    })
  }

  fun onCameraTextClick(camera: Camera?) {
    camera?.let {
      _cameraInfo.value = Event(camera)
    }
  }

  fun onBackClick() {
    _back.value = Event(Unit)
  }

}