package com.adrc95.unsplashsample.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.databinding.ViewPhotoBinding
import com.adrc95.unsplashsample.ui.common.extension.loadUrl

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ViewPhotoBinding.bind(view)

    fun render(photo: Photo) = with(binding) {
        picture.loadUrl(photo.url)
    }
}