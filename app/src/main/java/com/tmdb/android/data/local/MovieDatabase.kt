package com.tmdb.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tmdb.android.data.local.entity.RemoteKeys
import com.tmdb.android.domain.model.Movie

@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}