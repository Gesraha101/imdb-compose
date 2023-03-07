package com.example.ui.details.states

import com.example.core.ui.ViewState
import com.example.domain.models.Movie

sealed class MovieDetailsState : ViewState {
    class DisplayMovieDetails(val movie: Movie?) : MovieDetailsState()
}
