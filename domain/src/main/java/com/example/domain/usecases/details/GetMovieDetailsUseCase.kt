package com.example.domain.usecases.details

import com.example.core.domain.DataState
import com.example.domain.models.Movie
import com.example.domain.repos.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieRepository) {
    operator fun invoke(id: Int) : Flow<DataState<Movie>> {
        return repo.getMovieDetails(id)
    }
}