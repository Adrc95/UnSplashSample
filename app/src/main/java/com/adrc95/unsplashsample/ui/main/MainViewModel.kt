package com.adrc95.unsplashsample.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adrc95.unsplashsample.di.IO
import com.adrc95.usecase.GetPhotos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IO private val dispatcher: CoroutineDispatcher,
    private val getPhotos: GetPhotos
) : ViewModel() {
    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState> = _state.asStateFlow()

    init {
        loadPhotos()
    }

    private fun loadPhotos(page: Int = 1) {
        _state.value = MainViewState(loading = true)
        val params = GetPhotos.Params(page = page)
        viewModelScope.launch(dispatcher) {
            getPhotos.run(params).fold(ifLeft = {
                _state.value = MainViewState(error = it)
            }, ifRight = {
                _state.value = MainViewState(photos = it)
            })
        }
    }

    fun onLoadMore(page: Int) {
        loadPhotos(page)
    }
}
