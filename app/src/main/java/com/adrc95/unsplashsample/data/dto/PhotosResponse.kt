package com.adrc95.unsplashsample.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetPhotosResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("results")
    val results: List<PhotoDto>
)

@Serializable
data class PhotoDto(
    @SerialName("id")
    val id: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("width")
    val width: Long,
    @SerialName("height")
    val height: Long,
    @SerialName("color")
    val color: String,
    @SerialName("likes")
    val likes: Long,
    @SerialName("liked_by_user")
    val likedByUser: Boolean,
    @SerialName("description")
    val description: String?,
    @SerialName("user")
    val user: UserDto,
    @SerialName("current_user_collections")
    val currentUserCollections: List<String>,
    @SerialName("urls")
    val urls: UrlsDto,
    @SerialName("links")
    val links: LinksDto,
    @SerialName("exif")
    val exif: ExifDto? = null,
)

@Serializable
data class UserDto(
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImageDto?,
    @SerialName("location")
    val location: String?,
    @SerialName("links")
    val links: ProfileLinksDto?)

val PhotoDto.author: String
    get() =
        if (this.user.instagramUsername != null)
            "@${this.user.instagramUsername}"
        else
            this.user.username

@Serializable
data class UrlsDto(
    @SerialName("raw")
    val raw: String,
    @SerialName("full")
    val full: String,
    @SerialName("regular")
    val regular: String,
    @SerialName("small")
    val small: String,
    @SerialName("thumb")
    val thumb: String)

@Serializable
data class ProfileImageDto(
    @SerialName("small")
    val small: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("large")
    val large: String)

@Serializable
data class ProfileLinksDto(
    @SerialName("self")
    val self: String,
    @SerialName("html")
    val html: String,
    @SerialName("photos")
    val photos: String,
    @SerialName("likes")
    val likes: String)

@Serializable
data class LinksDto(
    @SerialName("self")
    val self: String,
    @SerialName("html")
    val html: String,
    @SerialName("download")
    val download: String)

@Serializable
data class ExifDto(
    @SerialName("aperture")
    val aperture: String?,
    @SerialName("exposure_time")
    val exposureTime: String?,
    @SerialName("focal_length")
    val focalLength: String?,
    @SerialName("iso")
    val iso: Int?,
    @SerialName("make")
    val make: String?,
    @SerialName("model")
    val model: String?,
    @SerialName("name")
    val name: String?
)