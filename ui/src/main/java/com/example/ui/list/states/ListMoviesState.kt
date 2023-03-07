package com.example.ui.list.states

import com.example.core.ui.ViewState
import com.example.domain.models.Movie

sealed class ListMoviesState : ViewState {
    open class ListMovies(val list: List<Movie>?) : ListMoviesState()

    class ListTopRatedMovies(list: List<Movie>?) : ListMovies(list)

    class ListMostPopularMovies(list: List<Movie>?) : ListMovies(list)
}
