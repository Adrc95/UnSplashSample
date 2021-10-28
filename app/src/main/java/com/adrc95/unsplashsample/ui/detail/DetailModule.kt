package com.adrc95.unsplashsample.ui.detail

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.unsplashsample.di.ActivityScope
import com.adrc95.usecase.GetPhoto
import com.adrc95.usecase.Invoker
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @ActivityScope
    @Provides
    fun provideDetailPresenter(invoker: Invoker, getPhoto: GetPhoto): DetailPresenter
            = DetailPresenter(invoker, getPhoto)

    @ActivityScope
    @Provides
    fun provideGetPhotoUseCase(photosRepository: PhotosRepository): GetPhoto
            = GetPhoto(photosRepository)
}