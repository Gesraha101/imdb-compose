package com.example.local.local

import com.squareup.moshi.Moshi

interface PreferencesDataStore {

    suspend fun putString(key: String, value: String)
    suspend fun putInt(key: String, value: Int)
    suspend fun putLong(key: String, value: Long)
    suspend fun putBoolean(key: String, value: Boolean)

    suspend fun getString(key: String): String?
    suspend fun getInt(key: String): Int?
    suspend fun getLong(key: String): Long?
    suspend fun getBoolean(key: String): Boolean

    suspend fun removeInt(key: String)
}

suspend inline fun <reified T> PreferencesDataStore.getObject(key: String): T? =
    getString(key)?.let { Moshi.Builder().build().adapter(T::class.java).fromJson(it) }

suspend inline fun <reified T> PreferencesDataStore.putObject(key: String, value: T) =
    putString(key, Moshi.Builder().build().adapter(T::class.java).toJson(value))