package com.tmdb.android.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    val name: String,
    val username: String,
    @SerializedName("avatar_path")
    val avatarPath: String?,
    val rating: Double
)