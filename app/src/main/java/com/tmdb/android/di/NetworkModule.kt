package com.tmdb.android.di

import androidx.viewbinding.BuildConfig
import com.tmdb.android.BuildConfig.API_KEY
import com.tmdb.android.BuildConfig.BASE_URL
import com.tmdb.android.data.remote.api.MovieApi
import com.tmdb.android.utils.QUERY_API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): MovieApi {
        val logger = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) setLevel(HttpLoggingInterceptor.Level.BODY)
            else setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor { chain ->
                var request = chain.request()
                val url = request.url
                    .newBuilder()
                    .addQueryParameter(QUERY_API_KEY, API_KEY)
                    .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }
}