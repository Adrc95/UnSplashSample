package com.adrc95.unsplashsample.ui.detail

import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo

data class DetailViewState(
    val loading : Boolean = false,
    val photo: Photo? = null,
    val error: ApiError? = null
)
