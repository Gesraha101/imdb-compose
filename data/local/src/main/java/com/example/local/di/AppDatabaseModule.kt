package com.example.local.di

import android.content.Context
import com.example.local.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppRoomDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMoviesDAO(db: AppRoomDatabase) = db.moviesDAO()
}