package com.adrc95.domain.model

data class Camera(
    val aperture: String,
    val exposureTime: String,
    val focalLength: String,
    val iso: Int,
    val make: String,
    val model: String,
    val name: String
)