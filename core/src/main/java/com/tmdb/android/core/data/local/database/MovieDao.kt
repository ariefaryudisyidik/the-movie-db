package com.tmdb.android.core.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.android.core.domain.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getAllMovie(): PagingSource<Int, Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}