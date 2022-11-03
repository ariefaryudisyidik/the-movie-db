package com.tmdb.android.data.remote.api

import com.tmdb.android.data.remote.response.GenreResponse
import com.tmdb.android.data.remote.response.MovieResponse
import com.tmdb.android.data.remote.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int? = null
    ): MovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreResponse

    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getVideos(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") videos: String = "videos"
    ): VideoResponse
}