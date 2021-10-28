package com.adrc95.unsplashsample.ui.main

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.unsplashsample.di.ActivityScope
import com.adrc95.unsplashsample.ui.common.navigator.Navigator
import com.adrc95.usecase.GetPhotos
import com.adrc95.usecase.Invoker
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @ActivityScope
    @Provides
    fun provideMainPresenter(navigator: Navigator, invoker: Invoker, getPhotos: GetPhotos): MainPresenter
            = MainPresenter(navigator, invoker, getPhotos)

    @ActivityScope
    @Provides
    fun provideGetPhotosUseCase(photosRepository: PhotosRepository): GetPhotos
            = GetPhotos(photosRepository)
}