package com.example.ui.details

import com.example.core.domain.DataState
import com.example.coreui.bases.BaseViewModel
import com.example.coreui.bases.PaginationDelegate
import com.example.coreui.bases.ScreenStateDelegate
import com.example.domain.models.Movie
import com.example.domain.usecases.details.GetMovieDetailsUseCase
import com.example.ui.details.actions.MovieDetailsAction
import com.example.ui.details.states.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    screenStateDelegate: ScreenStateDelegate,
    paginationDelegate: PaginationDelegate,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : BaseViewModel<MovieDetailsAction, MovieDetailsState>(screenStateDelegate, paginationDelegate) {

    var details =
        uiState.filter { it?.data is MovieDetailsState.DisplayMovieDetails }
            .collectAsStateFlow()

    override fun handleSuccessStatus(action: MovieDetailsAction, data: Any?): DataState<MovieDetailsState> {
        return when (action) {
            is MovieDetailsAction.GetMovieDetails -> DataState.Success(MovieDetailsState.DisplayMovieDetails(data as? Movie))
        }
    }

    override fun handleAction(action: MovieDetailsAction): Flow<DataState<out Any>> {
        return when (action) {
            is MovieDetailsAction.GetMovieDetails -> getMovieDetailsUseCase.invoke(action.id)
        }
    }
}