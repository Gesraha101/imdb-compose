package com.example.domain.models


data class Movie(
    val id: Int,
    val originalTitle: String?,
    val posterUrl: String?,
    val backdropUrl: String?,
    val overview: String?,
    val rate: Float?,
    val releaseDate: String?
)
