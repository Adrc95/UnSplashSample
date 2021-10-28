package com.adrc95.domain.model

data class Photo(val id: String,
                 val url: String,
                 val author: Author,
                 val camera: Camera?,
                 val description: String?,
                 val createdAt: String)
