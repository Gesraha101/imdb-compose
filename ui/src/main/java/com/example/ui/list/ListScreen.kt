package com.example.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.R
import com.example.core.domain.DataState
import com.example.coreui.components.AppScaffold
import com.example.coreui.components.SpinnerOption
import com.example.ui.AppHeader
import com.example.ui.list.models.MostPopular
import com.example.ui.list.states.ListMoviesState


@Composable
fun ListScreen(viewModel: MoviesListViewModel = hiltViewModel(), onMovieClick: (id: Int) -> Unit) {
    var sortMethod: SpinnerOption by remember { mutableStateOf(MostPopular) }

    val stateDelegate = viewModel.screenStateDelegate

    stateDelegate.StatesContainer {
        AppScaffold(
            error = error,
            errorShown = errorShown,
            isLoading = loadingShown,
            onErrorActionClick = { stateDelegate.hideError() },
            topBar = {
                AppHeader(
                    selected = sortMethod,
                    onSortMethodChanged = {
                        if (sortMethod != it)
                            sortMethod = it
                    })
            }) {

            val lazyListState = rememberLazyGridState()

            val shouldStartPaginate by remember {
                derivedStateOf {
                    viewModel.canPaginate &&
                            (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                                ?: -4) >= (lazyListState.layoutInfo.totalItemsCount - 6)
                }
            }

            val moviesWrapper by viewModel.movies.collectAsStateWithLifecycle()

            LaunchedEffect(sortMethod) {
                viewModel.resetPagination(sortMethod)
            }

            LaunchedEffect(shouldStartPaginate) {
                if (shouldStartPaginate)
                    viewModel.requestMovies(sortMethod)
            }

            MoviesList(moviesWrapper, lazyListState, onMovieClick)
        }
    }
}

@Composable
fun MoviesList(moviesState: DataState<ListMoviesState>?, listState: LazyGridState, onMovieClick: (id: Int) -> Unit) {
    val movies = (moviesState?.data as? ListMoviesState.ListMovies)?.list
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(185.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = movies ?: emptyList(), key = { it.id }) { movie ->
            MoviePoster(movie.posterUrl) {
                onMovieClick(movie.id)
            }
        }
        if (moviesState is DataState.PageSuccess && moviesState.extraPagesAvailable)
            loadingFooter()
    }
}

fun LazyGridScope.loadingFooter() {
    item(span = { GridItemSpan(2) }) {
        Row(horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 2.dp
            )
        }
    }
}

@Composable
fun MoviePoster(posterUrl: String?, onMovieClick: () -> Unit) {
    AsyncImage(
        modifier = Modifier
            .width(185.dp)
            .clickable {
                onMovieClick()
            },
        contentScale = ContentScale.FillWidth,
        model = ImageRequest.Builder(LocalContext.current).data(posterUrl).placeholder(R.drawable.placeholder)
            .build(),
        contentDescription = "MOVIE_POSTER"
    )
}