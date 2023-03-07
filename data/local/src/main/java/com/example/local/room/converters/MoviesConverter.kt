package com.example.local.room.converters

import androidx.room.TypeConverter
import com.example.local.utils.ConverterUtils
import com.example.repo.models.responses.RawMovie

class MoviesConverter {
    @TypeConverter
    fun fromString(value: String?): MutableList<RawMovie>? {
        return ConverterUtils.fromString(value)
    }

    @TypeConverter
    fun toString(tokens: MutableList<RawMovie>?): String? {
        return ConverterUtils.toString(tokens)
    }
}