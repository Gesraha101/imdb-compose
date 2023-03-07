package com.example.remote.di

import com.example.remote.dataproviders.DetailsRemoteDataProviderImpl
import com.example.remote.dataproviders.ListRemoteDataProviderImpl
import com.example.repo.dataproviders.remote.DetailsRemoteDataProvider
import com.example.repo.dataproviders.remote.ListRemoteDataProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataProviderModule {

    @Binds
    abstract fun bindListServiceImpl(listDataProvider: ListRemoteDataProviderImpl): ListRemoteDataProvider

    @Binds
    abstract fun bindDetailsServiceImpl(listDataProvider: DetailsRemoteDataProviderImpl): DetailsRemoteDataProvider
}
