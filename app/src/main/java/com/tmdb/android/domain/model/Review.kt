package com.tmdb.android.domain.model

import com.tmdb.android.data.remote.response.AuthorDetails

data class Review(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val id: String,
    val updatedAt: String,
)