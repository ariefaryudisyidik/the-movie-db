package com.tmdb.android.data.remote.response

import com.google.gson.annotations.SerializedName
import com.tmdb.android.domain.model.Video

data class VideoResult(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("iso_3166_1")
    val iso31661: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    @SerializedName("published_at")
    val publishedAt: String,
    val id: String
)

fun VideoResult.toDomain(): Video {
    return Video(
        name = name,
        key = key,
        type = type,
        id = id
    )
}
