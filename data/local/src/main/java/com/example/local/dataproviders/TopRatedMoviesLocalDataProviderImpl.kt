package com.example.local.dataproviders

import com.example.local.room.common.MovieType
import com.example.local.room.common.MoviesDAO
import com.example.local.room.common.MoviesEntity
import com.example.repo.dataproviders.local.TopRatedMoviesLocalDataProvider
import com.example.repo.models.responses.RawMovie
import javax.inject.Inject

class TopRatedMoviesLocalDataProviderImpl @Inject constructor(
    private val moviesDAO: MoviesDAO
): TopRatedMoviesLocalDataProvider {

    override suspend fun hasData(): Boolean {
        return moviesDAO.getCount() != 0
    }

    override suspend fun saveData(list: List<RawMovie>) {
        moviesDAO.insert(MoviesEntity(MovieType.TOP_RATED.value, list))
    }

    override suspend fun getData(): List<RawMovie> {
        return moviesDAO.fetch(MovieType.TOP_RATED.value)?.movies ?: emptyList()
    }
}