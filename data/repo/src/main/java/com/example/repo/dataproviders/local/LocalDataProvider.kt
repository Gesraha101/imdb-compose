package com.example.repo.dataproviders.local

interface LocalDataProvider<T> {
    suspend fun hasData(): Boolean

    suspend fun saveData(list: List<T>)

    suspend fun getData(): List<T>
}