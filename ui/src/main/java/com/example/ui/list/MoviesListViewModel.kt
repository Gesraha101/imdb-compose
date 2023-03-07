package com.example.ui.list

import com.example.core.domain.DataState
import com.example.coreui.bases.BaseViewModel
import com.example.coreui.bases.PaginationDelegate
import com.example.coreui.bases.ScreenStateDelegate
import com.example.coreui.components.SpinnerOption
import com.example.domain.models.Movie
import com.example.domain.usecases.list.ListMostPopularMoviesUseCase
import com.example.domain.usecases.list.ListTopRatedMoviesUseCase
import com.example.ui.list.actions.ListMoviesAction
import com.example.ui.list.models.MostPopular
import com.example.ui.list.models.TopRated
import com.example.ui.list.states.ListMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    screenStateDelegate: ScreenStateDelegate,
    paginationDelegate: PaginationDelegate,
    val topRatedMoviesUseCase: ListTopRatedMoviesUseCase,
    val mostPopularMoviesUseCase: ListMostPopularMoviesUseCase
) : BaseViewModel<ListMoviesAction, ListMoviesState>(screenStateDelegate, paginationDelegate) {

    var movies =
        uiState.filter { it?.data is ListMoviesState.ListMovies }
            .collectAsStateFlow()
    val canPaginate get() = paginationDelegate.paginationAllowed

    override fun handleSuccessStatus(action: ListMoviesAction, data: Any?): DataState<ListMoviesState> {
        return when (action) {
            is ListMoviesAction.GetMostPopularMovies -> DataState.PageSuccess(
                ListMoviesState.ListMostPopularMovies(data as? List<Movie>),
                extraPagesAvailable = paginationDelegate.extraPagesAvailable
            )
            is ListMoviesAction.GetTopRatedMovies -> DataState.PageSuccess(
                ListMoviesState.ListTopRatedMovies(data as? List<Movie>),
                extraPagesAvailable = paginationDelegate.extraPagesAvailable
            )
        }
    }

    override fun handleAction(action: ListMoviesAction): Flow<DataState<out Any>> {
        return when (action) {
            is ListMoviesAction.GetMostPopularMovies -> mostPopularMoviesUseCase.invoke(paginationDelegate.page)
            is ListMoviesAction.GetTopRatedMovies -> topRatedMoviesUseCase.invoke(paginationDelegate.page)
        }
    }

    fun resetPagination(sortMethod: SpinnerOption) {
        paginationDelegate.reset()
        requestMovies(sortMethod)
    }

    fun requestMovies(sortMethod: SpinnerOption) {
        if (sortMethod == MostPopular)
            dispatchPagingAction(ListMoviesAction.GetMostPopularMovies)
        else if (sortMethod == TopRated)
            dispatchPagingAction(ListMoviesAction.GetTopRatedMovies)
    }
}