package com.adrc95.unsplashsample.ui.main

import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val invoker: Invoker,
                                         private val getPhotos: GetPhotos) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state : StateFlow<MainViewState> = _state.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos(page: Int = 1) {
        _state.update { state -> state.copy(loading = true) }
        val params = GetPhotos.Params(page = page)
        invoker.execute(getPhotos, params, ::onPhotosArrived)
    }

    private fun onPhotosArrived(result: Either<ApiError, List<Photo>>) {
        result.fold(ifLeft = {
            _state.update { state -> state.copy(loading = false) }
        }, ifRight = {
            _state.update { state -> state.copy(loading = false, photos = it) }
        })
    }

    fun onLoadMore(page: Int) {
        loadPhotos(page)
    }

}
