package com.adrc95.data.repository

import arrow.core.Either
import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.domain.model.Photo
import com.adrc95.domain.exception.ApiError

class PhotosRepository(private val networkDataSource: PhotosNetworkDataSource) {

  suspend fun getPhotos(page: Int): Either<ApiError, List<Photo>> = networkDataSource.getPhotos(page)

  suspend fun getPhoto(photoId: String): Either<ApiError, Photo> = networkDataSource.getPhoto(photoId)

}