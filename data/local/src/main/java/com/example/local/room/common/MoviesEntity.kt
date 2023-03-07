package com.example.local.room.common

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.repo.models.responses.RawMovie

@Entity
data class MoviesEntity(
    @PrimaryKey var id: String,
    @TypeConverters
    var movies: List<RawMovie>? = null
)