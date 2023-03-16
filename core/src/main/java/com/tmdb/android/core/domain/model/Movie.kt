package com.tmdb.android.core.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tmdb.android.core.utils.IMAGE_URL
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    val id: Int,
    val backdropPath: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable {
    fun backdropPathUrl() = IMAGE_URL + backdropPath
    fun posterPathUrl() = IMAGE_URL + posterPath
}