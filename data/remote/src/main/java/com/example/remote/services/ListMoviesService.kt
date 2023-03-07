package com.example.remote.services

import com.example.repo.models.responses.ListResponse
import com.example.repo.models.responses.RawMovie
import retrofit2.http.GET
import retrofit2.http.Query

interface ListMoviesService {
    @GET("movie/popular")
    suspend fun getMostPopularMovies(@Query("page") page: Int): ListResponse<RawMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): ListResponse<RawMovie>
}