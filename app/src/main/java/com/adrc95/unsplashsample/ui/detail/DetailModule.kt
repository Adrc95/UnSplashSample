package com.adrc95.unsplashsample.ui.detail

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.usecase.GetPhoto
import com.adrc95.usecase.Invoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
class DetailModule {

    @Provides
    fun provideDetailPresenter(invoker: Invoker, getPhoto: GetPhoto): DetailPresenter
            = DetailPresenter(invoker, getPhoto)

    @Provides
    fun provideGetPhotoUseCase(photosRepository: PhotosRepository): GetPhoto
            = GetPhoto(photosRepository)
}