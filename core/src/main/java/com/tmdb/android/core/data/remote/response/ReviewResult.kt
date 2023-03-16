package com.tmdb.android.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.tmdb.android.core.domain.model.Review

data class ReviewResult(
    val author: String,
    @SerializedName("author_details")
    val authorDetails: AuthorDetails,
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    val id: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String
)

fun ReviewResult.toDomain(): Review {
    return Review(
        author = author,
        authorDetails = authorDetails,
        content = content,
        id = id,
        updatedAt = updatedAt
    )
}