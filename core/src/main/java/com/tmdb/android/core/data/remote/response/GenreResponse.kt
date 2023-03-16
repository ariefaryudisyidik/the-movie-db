package com.tmdb.android.core.data.remote.response

import com.tmdb.android.core.domain.model.Genre

data class GenreResponse(
    val genres: List<Genre>
)