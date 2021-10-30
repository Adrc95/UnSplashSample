package com.adrc95.unsplashsample.ui.detail

import com.adrc95.domain.model.Photo

sealed class DetailViewState {
    object Loading : DetailViewState()
    class RenderPhoto(val photo: Photo) : DetailViewState()
    object LoadPhotoDetail : DetailViewState()
}

