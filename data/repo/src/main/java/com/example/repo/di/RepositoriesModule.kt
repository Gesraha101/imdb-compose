package com.example.repo.di

import com.example.domain.repos.MovieRepository
import com.example.repo.repos.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindsMovieRepo(repo: MovieRepositoryImpl): MovieRepository
}