package com.adrc95.unsplashsample.ui.common.extension

import android.content.Context
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.exception.HttpError
import com.adrc95.domain.exception.NetworkError
import com.adrc95.domain.exception.UnknownApiError
import com.adrc95.unsplashsample.R

fun ApiError.message(context: Context) =
    when (this) {
        is HttpError -> context.getString(R.string.loading_photo_error)
        is NetworkError -> context.getString(R.string.loading_photo_error)
        is UnknownApiError -> context.getString(R.string.loading_photo_error)
    }
