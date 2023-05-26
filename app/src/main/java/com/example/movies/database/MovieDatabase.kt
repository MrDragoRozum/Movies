package com.example.movies.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movies.pojo.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    companion object {
        private var database: MovieDatabase? = null
        private const val DB_NAME = "movie.db"

        fun getInstance(application: Application): MovieDatabase {
            database?.let { return it }
            val instance = Room.databaseBuilder(
                application,
                MovieDatabase::class.java,
                DB_NAME
            ).build()
            database = instance
            return instance
        }
    }
    abstract fun movieDao(): MovieDao
}