package com.example.local.dataproviders

import com.example.local.room.common.MovieType
import com.example.local.room.common.MoviesDAO
import com.example.local.room.common.MoviesEntity
import com.example.repo.dataproviders.local.MostPopularMoviesLocalDataProvider
import com.example.repo.models.responses.RawMovie
import javax.inject.Inject

class MostPopularMoviesLocalDataProviderImpl @Inject constructor(
    private val moviesDAO: MoviesDAO
) : MostPopularMoviesLocalDataProvider {

    override suspend fun hasData(): Boolean {
        return moviesDAO.getCount() != 0
    }

    override suspend fun saveData(list: List<RawMovie>) {
        moviesDAO.insert(MoviesEntity(MovieType.MOST_POPULAR.value, list))
    }

    override suspend fun getData(): List<RawMovie> {
        return moviesDAO.fetch(MovieType.MOST_POPULAR.value)?.movies ?: emptyList()
    }
}