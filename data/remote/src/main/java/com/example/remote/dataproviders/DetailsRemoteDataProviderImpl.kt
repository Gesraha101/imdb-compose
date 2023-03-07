package com.example.remote.dataproviders

import com.example.remote.services.MovieDetailsService
import com.example.repo.dataproviders.remote.DetailsRemoteDataProvider
import com.example.repo.models.responses.RawMovie
import javax.inject.Inject

class DetailsRemoteDataProviderImpl @Inject constructor(private val service: MovieDetailsService) : DetailsRemoteDataProvider {
    override suspend fun getMovieDetails(id: Int): RawMovie {
        return service.getMovieDetails(id)
    }
}