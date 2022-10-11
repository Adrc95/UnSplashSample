package com.adrc95.unsplashsample.di

import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.unsplashsample.data.datasource.UnsplashPhotosDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun binPhotoNetworkDataSource(unsplashPhotosDataSource: UnsplashPhotosDataSource)
    : PhotosNetworkDataSource
}