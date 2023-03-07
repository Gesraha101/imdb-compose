package com.example.repo.dataproviders.remote

import com.example.repo.models.responses.ListResponse
import com.example.repo.models.responses.RawMovie

interface ListRemoteDataProvider {
    suspend fun getTopRatedMovies(page: Int): ListResponse<RawMovie>

    suspend fun getMostPopularMovies(page: Int): ListResponse<RawMovie>
}