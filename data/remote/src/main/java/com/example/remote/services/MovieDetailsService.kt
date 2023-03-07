package com.example.remote.services

import com.example.repo.models.responses.RawMovie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): RawMovie
}