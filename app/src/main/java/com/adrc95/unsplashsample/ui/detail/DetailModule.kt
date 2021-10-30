package com.adrc95.unsplashsample.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.adrc95.data.repository.PhotosRepository
import com.adrc95.usecase.GetPhoto
import com.adrc95.usecase.Invoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DetailModule {

    @Provides
    fun provideDetailPresenter(stateHandle: SavedStateHandle, invoker: Invoker,
                               getPhoto: GetPhoto): DetailViewModel
            = DetailViewModel(stateHandle, invoker, getPhoto)

    @Provides
    fun provideGetPhotoUseCase(photosRepository: PhotosRepository): GetPhoto
            = GetPhoto(photosRepository)
}