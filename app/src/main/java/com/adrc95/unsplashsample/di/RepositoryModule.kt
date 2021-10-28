package com.adrc95.unsplashsample.di

import com.adrc95.data.source.PhotosNetworkDataSource
import com.adrc95.data.repository.PhotosRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesPhotosRepository(networkDataSource: PhotosNetworkDataSource): PhotosRepository =
        PhotosRepository(networkDataSource)
}