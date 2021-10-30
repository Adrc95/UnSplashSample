package com.adrc95.unsplashsample.ui.main

import com.adrc95.domain.model.Photo

sealed class MainViewState {
        object Loading : MainViewState()
        class ShowPhotos(val photos: List<Photo>) : MainViewState()
        object LoadPhotos : MainViewState()
    }
