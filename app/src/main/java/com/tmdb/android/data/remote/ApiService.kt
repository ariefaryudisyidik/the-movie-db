package com.tmdb.android.data.remote

import com.tmdb.android.data.remote.response.TopRatedMovieResponse
import com.tmdb.android.data.remote.response.UpcomingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int
    ): TopRatedMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int
    ): TopRatedMovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int
    ): UpcomingMovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int,
        @Query("total_pages") totalPages: Int
    ): UpcomingMovieResponse
}