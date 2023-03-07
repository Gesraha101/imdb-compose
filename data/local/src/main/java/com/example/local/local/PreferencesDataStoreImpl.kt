package com.example.local.local


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject


class PreferencesDataStoreImpl @Inject constructor(var dataStore: DataStore<Preferences>) :
    PreferencesDataStore {

    override suspend fun putString(key: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        dataStore.edit {
            it[intPreferencesKey(key)] = value
        }
    }

    override suspend fun putLong(key: String, value: Long) {
        dataStore.edit {
            it[longPreferencesKey(key)] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            dataStore.data.first()[stringPreferencesKey((key))]
        } catch (e: IOException) {
            return null
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            dataStore.data.first()[intPreferencesKey((key))]
        } catch (e: IOException) {
            return 0
        }
    }

    override suspend fun getLong(key: String): Long? {
        return try {
            dataStore.data.first()[longPreferencesKey((key))]
        } catch (e: IOException) {
            return null
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        return try {
            dataStore.data.first()[booleanPreferencesKey((key))] ?: false
        } catch (e: IOException) {
            return false
        }
    }

    override suspend fun removeInt(key: String) {
        dataStore.edit {
            it.remove(intPreferencesKey(key))
        }
    }
}