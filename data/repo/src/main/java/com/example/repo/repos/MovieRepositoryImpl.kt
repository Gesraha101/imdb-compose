package com.example.repo.repos

import com.example.core.domain.DataState
import com.example.domain.models.Movie
import com.example.domain.repos.MovieRepository
import com.example.repo.NetworkBoundResource
import com.example.repo.dataproviders.local.LocalDataProvider
import com.example.repo.dataproviders.local.MostPopularMoviesLocalDataProvider
import com.example.repo.dataproviders.local.TopRatedMoviesLocalDataProvider
import com.example.repo.dataproviders.remote.DetailsRemoteDataProvider
import com.example.repo.dataproviders.remote.ListRemoteDataProvider
import com.example.repo.mappers.MovieMapper
import com.example.repo.models.responses.ListResponse
import com.example.repo.models.responses.RawMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataProvider: ListRemoteDataProvider,
    private val movieDetailsDataProvider: DetailsRemoteDataProvider,
    private val movieMapper: MovieMapper,
    private val topRatedDataProvider: TopRatedMoviesLocalDataProvider,
    private val mostPopularDataProvider: MostPopularMoviesLocalDataProvider,
) : MovieRepository {

    override fun getTopRatedMovies(page: Int): Flow<DataState<List<Movie>>> {
        return getMovies(topRatedDataProvider) { remoteDataProvider.getTopRatedMovies(page) }
    }

    override fun getMostPopularMovies(page: Int): Flow<DataState<List<Movie>>> {
        return getMovies(mostPopularDataProvider) { remoteDataProvider.getMostPopularMovies(page) }
    }

    override fun getMovieDetails(id: Int): Flow<DataState<Movie>> {

        return object : NetworkBoundResource<RawMovie, Movie>() {
            override fun shouldRemoteFetch(): Boolean = true

            override suspend fun fetchFromNetwork(): RawMovie {
                return movieDetailsDataProvider.getMovieDetails(id)
            }

            override fun handleApiSuccessResponse(response: RawMovie): DataState<Movie> {
                return DataState.Success(movieMapper.toDomainLayer(response))
            }
        }.asFlow()
    }

    private fun getMovies(
        localDataProvider: LocalDataProvider<RawMovie>,
        fetchFromNetwork: suspend () -> ListResponse<RawMovie>
    ): Flow<DataState<List<Movie>>> {

        return object : NetworkBoundResource<ListResponse<RawMovie>, List<Movie>>() {
            override fun shouldRemoteFetch(): Boolean = true

            override suspend fun fetchFromNetwork(): ListResponse<RawMovie> {
                return fetchFromNetwork()
            }

            override fun handleApiSuccessResponse(response: ListResponse<RawMovie>): DataState<List<Movie>> {
                return DataState.Success(response.results.map { movieMapper.toDomainLayer(it) })
            }

            override suspend fun cacheRawResponse(item: ListResponse<RawMovie>) {
                localDataProvider.saveData(item.results)
            }

            override suspend fun loadFromCache(): ListResponse<RawMovie> {
                val list = localDataProvider.getData()
                return ListResponse(list)
            }

            override suspend fun hasCachedData(): Boolean {
                return localDataProvider.hasData()
            }
        }.asFlow()
    }
}