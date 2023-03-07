package com.example.repo.di

import com.example.core.data.AbstractMapper
import com.example.domain.models.Movie
import com.example.repo.mappers.MovieMapper
import com.example.repo.models.responses.RawMovie
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {
    @Binds
    abstract fun bindsMovieMapper(mapper: MovieMapper): AbstractMapper<RawMovie, Movie>
}