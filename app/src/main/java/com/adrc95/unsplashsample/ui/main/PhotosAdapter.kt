package com.adrc95.unsplashsample.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.R
import com.adrc95.unsplashsample.ui.common.extension.basicDiffUtil
import com.adrc95.unsplashsample.ui.common.extension.inflate

class PhotosAdapter(private val listener: (Photo) -> Unit) :
RecyclerView.Adapter<PhotoViewHolder>() {

    var photos: List<Photo> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = parent.inflate(R.layout.view_photo, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.render(photo)
        holder.itemView.setOnClickListener { listener(photo) }
    }
}