package com.adrc95.usecase

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.domain.model.Photo
import javax.inject.Inject

class GetPhoto @Inject constructor(private val photosRepository: PhotosRepository)
  : UseCase<GetPhoto.Params, Photo>() {

  override suspend fun run(params: Params) = photosRepository.getPhoto(params.photoId)

  data class Params(val photoId: String)
}