package com.example.domain.repos

import com.example.core.domain.DataState
import com.example.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getTopRatedMovies(page: Int) : Flow<DataState<List<Movie>>>

    fun getMostPopularMovies(page: Int) : Flow<DataState<List<Movie>>>

    fun getMovieDetails(id: Int) : Flow<DataState<Movie>>
}