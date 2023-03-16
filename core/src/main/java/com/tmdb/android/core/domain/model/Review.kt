package com.tmdb.android.core.domain.model

import com.tmdb.android.core.data.remote.response.AuthorDetails

data class Review(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val id: String,
    val updatedAt: String,
)