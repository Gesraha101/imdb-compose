package com.example.repo.models.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RawMovie(
    val id: Int? = null,
    @Json(name = "original_title") val originalTitle: String? = null,
    @Json(name = "overview") val overview: String? = null,
    @Json(name = "poster_path") val posterPath: String? = null,
    @Json(name = "backdrop_path") val backdropPath: String? = null,
    @Json(name = "release_date") val releaseDate: String? = null,
    @Json(name = "vote_average") val rate: Float? = null
)
