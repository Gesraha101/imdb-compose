package com.example.repo.mappers

import com.example.core.data.AbstractMapper
import com.example.domain.models.Movie
import com.example.repo.models.responses.RawMovie
import javax.inject.Inject

class MovieMapper @Inject constructor() : AbstractMapper<RawMovie, Movie> {
    override fun toDomainLayer(data: RawMovie): Movie {
        return Movie(
            data.id ?: 0,
            data.originalTitle,
            "http://image.tmdb.org/t/p/w185" + data.posterPath,
            "http://image.tmdb.org/t/p/w185" + data.backdropPath,
            data.overview,
            data.rate,
            data.releaseDate
        )
    }

    override fun toDataLayer(domain: Movie): RawMovie {
        return RawMovie()
    }
}