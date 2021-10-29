package com.adrc95.unsplashsample.di

import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.data.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPhotosRepository(networkDataSource: PhotosNetworkDataSource): PhotosRepository =
        PhotosRepository(networkDataSource)
}