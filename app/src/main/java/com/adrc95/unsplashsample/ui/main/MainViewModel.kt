package com.adrc95.unsplashsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.ui.common.Event
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val invoker: Invoker,
                                         private val getPhotos: GetPhotos) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState.LoadPhotos)
    val state : StateFlow<MainViewState> = _state.asStateFlow()

    private val _navigateToPhotoDetail = MutableLiveData<Event<Photo>>()
    val navigateToPhotoDetail: LiveData<Event<Photo>> = _navigateToPhotoDetail

    fun onLoadPhotos() {
        loadPhotos()
    }

    private fun loadPhotos(page: Int = 1) {
        _state.value = MainViewState.Loading
        val params = GetPhotos.Params(page = page)
        invoker.execute(getPhotos, params, ::onPhotosArrived)
    }

    private fun onPhotosArrived(result: Either<ApiError, List<Photo>>) {
        result.fold(ifLeft = {

        }, ifRight = {
            _state.value = MainViewState.ShowPhotos(it)
        })
    }

    fun onLoadMore(page: Int) {
        loadPhotos(page)
    }

    fun onPhotoClicked(photo : Photo) {
        _navigateToPhotoDetail.value = Event(photo)
    }
}
