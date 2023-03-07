package com.example.ui.list.actions

import com.example.core.ui.ViewAction

sealed class ListMoviesAction : ViewAction {
    object GetTopRatedMovies : ListMoviesAction()

    object GetMostPopularMovies : ListMoviesAction()
}
