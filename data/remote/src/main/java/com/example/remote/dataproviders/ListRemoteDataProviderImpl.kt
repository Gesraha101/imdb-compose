package com.example.remote.dataproviders

import com.example.remote.services.ListMoviesService
import com.example.repo.dataproviders.remote.ListRemoteDataProvider
import com.example.repo.models.responses.ListResponse
import com.example.repo.models.responses.RawMovie
import javax.inject.Inject

class ListRemoteDataProviderImpl @Inject constructor(private val service: ListMoviesService) : ListRemoteDataProvider {
    override suspend fun getTopRatedMovies(page: Int): ListResponse<RawMovie> {
        return service.getTopRatedMovies(page)
    }

    override suspend fun getMostPopularMovies(page: Int): ListResponse<RawMovie> {
        return service.getMostPopularMovies(page)
    }

}