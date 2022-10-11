package com.adrc95.usecase

import com.adrc95.data.repository.PhotosRepository
import com.adrc95.domain.model.Photo
import javax.inject.Inject

class GetPhotos @Inject constructor(private val photosRepository: PhotosRepository) :
    UseCase<GetPhotos.Params, List<Photo>>() {

  override suspend fun run(params: Params) = photosRepository.getPhotos(params.page)

  data class Params(val page: Int)

}