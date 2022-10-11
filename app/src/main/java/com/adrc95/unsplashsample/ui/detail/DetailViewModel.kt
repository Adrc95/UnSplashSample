package com.adrc95.unsplashsample.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrc95.unsplashsample.di.IO
import com.adrc95.unsplashsample.ui.navigation.NavArg
import com.adrc95.usecase.GetPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  @IO private val dispatcher: CoroutineDispatcher,
  private val getPhoto: GetPhoto
) : ViewModel() {
  private val _state = MutableStateFlow(DetailViewState())
  val state: StateFlow<DetailViewState> = _state.asStateFlow()

  private val photoId = savedStateHandle.get<String>(NavArg.PhotoID.key) ?: ""

  init {
    loadPhoto()
  }

  private fun loadPhoto() {
    _state.value = DetailViewState(loading = true)
    val params = GetPhoto.Params(photoId)
    viewModelScope.launch(dispatcher) {
      getPhoto.run(params).fold(ifLeft = {
        _state.value = DetailViewState(error = it)
      }, ifRight = {
        _state.value = DetailViewState(photo = it)
      })
    }
  }
}