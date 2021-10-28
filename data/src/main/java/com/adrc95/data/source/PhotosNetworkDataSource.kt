package com.adrc95.data.source

import arrow.core.Either
import com.adrc95.domain.model.Photo
import com.adrc95.domain.exception.ApiError


interface PhotosNetworkDataSource {

    suspend fun getPhotos(page :Int): Either<ApiError, List<Photo>>

    suspend fun getPhoto(photoId: String): Either<ApiError, Photo>
}