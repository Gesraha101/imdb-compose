package com.example.local.di

import com.example.local.dataproviders.MostPopularMoviesLocalDataProviderImpl
import com.example.local.dataproviders.TopRatedMoviesLocalDataProviderImpl
import com.example.repo.dataproviders.local.MostPopularMoviesLocalDataProvider
import com.example.repo.dataproviders.local.TopRatedMoviesLocalDataProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataProviderModule {

    @Binds
    abstract fun bindTopMoviesLocalDataSource(topRatedMoviesLocalDataProviderImpl: TopRatedMoviesLocalDataProviderImpl): TopRatedMoviesLocalDataProvider

    @Binds
    abstract fun bindPopularMoviesLocalDataSource(mostPopularMoviesLocalDataProviderImpl: MostPopularMoviesLocalDataProviderImpl): MostPopularMoviesLocalDataProvider
}
