package com.adrc95.unsplashsample.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.ui.detail.DetailActivity

fun Activity.buildMainState(
    context: Context = this
) = MainState(context)

class MainState(private val context: Context) {

    fun onPhotoClicked(photo: Photo) {
        val intent = DetailActivity.getIntent(context, photo.id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}