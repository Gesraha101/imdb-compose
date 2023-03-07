package com.example.local.room.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(moviesEntity: MoviesEntity)

    @Query("Select * From MoviesEntity Where id = :id")
    suspend fun fetch(id: String): MoviesEntity?

    @Query("Select COUNT(id) From MoviesEntity")
    suspend fun getCount(): Int
}

enum class MovieType(val value: String) {
    TOP_RATED("TOP_RATED"), MOST_POPULAR("MOST_POPULAR")
}