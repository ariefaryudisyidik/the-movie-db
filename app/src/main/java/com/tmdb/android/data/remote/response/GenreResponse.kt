package com.tmdb.android.data.remote.response

import com.tmdb.android.domain.model.Genre

data class GenreResponse(
    val genres: List<Genre>
)