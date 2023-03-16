package com.tmdb.android.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<ReviewResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)