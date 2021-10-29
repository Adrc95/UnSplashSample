package com.adrc95.unsplashsample.ui.main

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.unsplashsample.ui.common.navigator.Navigator
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    fun provideMainPresenter(navigator: Navigator, invoker: Invoker, getPhotos: GetPhotos): MainPresenter
            = MainPresenter(navigator, invoker, getPhotos)

    @Provides
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotos
            = GetPhotos(photosRepository)
}