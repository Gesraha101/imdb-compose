package com.example.remote.services.retrofit

import retrofit2.Retrofit

class RetrofitClient(var client: Retrofit) {

    inline fun <reified T> get(): T {
       return client.create(T::class.java)
    }
}