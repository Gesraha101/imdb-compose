package com.example.remote.di

import com.example.remote.services.ListMoviesService
import com.example.remote.services.MovieDetailsService
import com.example.remote.services.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun providesListService(client: RetrofitClient) : ListMoviesService = client.get()

    @Provides
    @Singleton
    fun providesDetailsService(client: RetrofitClient) : MovieDetailsService = client.get()
}