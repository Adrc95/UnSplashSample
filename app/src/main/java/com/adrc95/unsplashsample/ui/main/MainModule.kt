package com.adrc95.unsplashsample.ui.main

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainViewModel(invoker: Invoker, getPhotos: GetPhotos): MainViewModel
            = MainViewModel(invoker, getPhotos)

    @Provides
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotos
            = GetPhotos(photosRepository)
}