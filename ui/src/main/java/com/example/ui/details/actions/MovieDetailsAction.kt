package com.example.ui.details.actions

import com.example.core.ui.ViewAction

sealed class MovieDetailsAction : ViewAction {
    class GetMovieDetails(val id: Int) : MovieDetailsAction()
}
