package com.adrc95.unsplashsample.di

import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.unsplashsample.data.service.APIService
import com.adrc95.unsplashsample.data.service.UnsplashApiService
import com.adrc95.unsplashsample.data.datasource.UnsplashPhotosDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun providesPhotosDataSource(api : APIService<UnsplashApiService>): PhotosNetworkDataSource =
        UnsplashPhotosDataSource(api)
}