package com.adrc95.unsplashsample.ui.common.navigator

import android.content.Context
import android.content.Intent
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.ui.detail.DetailActivity

class UnsplashAppNavigator(private val context: Context) : Navigator {

  override fun openDetail(photo: Photo) {
    val intent = DetailActivity.getIntent(context, photo.id)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
  }

}