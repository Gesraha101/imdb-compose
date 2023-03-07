package com.example.repo.dataproviders.remote

import com.example.repo.models.responses.RawMovie

interface DetailsRemoteDataProvider {
    suspend fun getMovieDetails(id: Int): RawMovie
}