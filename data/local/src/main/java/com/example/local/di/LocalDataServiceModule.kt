package com.example.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.local.APP_SETTING_PREF
import com.example.local.local.PreferencesDataStore
import com.example.local.local.PreferencesDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
object LocalDataServiceModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = {
                appContext.preferencesDataStoreFile(APP_SETTING_PREF)
            }
        )

    @Provides
    @Singleton
    fun provideLocalDataStore(dataStore: DataStore<Preferences>): PreferencesDataStore =
        PreferencesDataStoreImpl(dataStore)
}