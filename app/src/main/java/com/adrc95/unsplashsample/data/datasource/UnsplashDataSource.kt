package com.adrc95.unsplashsample.data.datasource

import arrow.core.Either
import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.domain.exception.ApiError
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.data.mapper.toDomain
import com.adrc95.unsplashsample.data.service.APIService
import com.adrc95.unsplashsample.data.service.UnsplashApiService
import javax.inject.Inject

class UnsplashPhotosDataSource @Inject constructor(
    private val unSplashApi: APIService<UnsplashApiService>) : PhotosNetworkDataSource {

    override suspend fun getPhotos(page: Int): Either<ApiError, List<Photo>> =
        unSplashApi.service.getPhotos(page).map { it.toDomain() }

    override suspend fun getPhoto(photoId: String): Either<ApiError, Photo> =
        unSplashApi.service.getPhoto(photoId).map { it.toDomain() }
}