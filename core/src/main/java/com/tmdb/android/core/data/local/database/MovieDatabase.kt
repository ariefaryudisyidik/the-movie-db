package com.tmdb.android.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tmdb.android.core.data.local.entity.RemoteKeys
import com.tmdb.android.core.domain.model.Movie

@Database(
    entities = [Movie::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}