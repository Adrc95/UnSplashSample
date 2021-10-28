package com.adrc95.unsplashsample.data.mapper

import com.adrc95.domain.model.Author
import com.adrc95.domain.model.Camera
import com.adrc95.domain.model.Photo
import com.adrc95.unsplashsample.data.dto.ExifDto
import com.adrc95.unsplashsample.data.dto.PhotoDto
import com.adrc95.unsplashsample.data.dto.UserDto

fun List<PhotoDto>.toDomain(): List<Photo> = this.map { it.toDomain() }

fun PhotoDto.toDomain(): Photo = Photo(
    this.id,
    this.urls.regular,
    this.user.toDomain(),
    this.exif?.toDomain(),
    this.description,
    this.createdAt)

fun UserDto.toDomain(): Author = Author(
    this.username,
    this.name ?: "",
    this.firstName ?: "",
    this.lastName ?: "",
    this.profileImage?.medium ?: "",
    this.location ?: "")

fun ExifDto.toDomain() : Camera = Camera(
    this.aperture ?:"",
    this.exposureTime ?:"",
    this.focalLength ?:"",
    this.iso ?: -1,
    this.make ?:"",
    this.model?:"",
    this.name?:""
)