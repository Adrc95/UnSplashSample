package com.adrc95.unsplashsample.ui.main

import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo

data class MainViewState(
    val loading : Boolean = false,
    val photos : List<Photo> = arrayListOf(),
    val error: ApiError? = null
)