package com.adrc95.unsplashsample.data.service

import arrow.core.Either
import com.adrc95.domain.exception.ApiError
import com.adrc95.unsplashsample.data.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {

    companion object {
        const val ITEMS_PER_PAGE = 30
    }

    @GET("/photos")
    suspend fun getPhotos(@Query("page") page: Int,
                  @Query("per_page") perPage: Int = ITEMS_PER_PAGE
    ): Either<ApiError, List<PhotoDto>>


    @GET("/photos/{id}")
    suspend fun getPhoto(@Path("id") id: String): Either<ApiError, PhotoDto>
}