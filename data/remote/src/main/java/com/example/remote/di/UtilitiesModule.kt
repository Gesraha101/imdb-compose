package com.example.remote.di

import com.example.remote.BuildConfig
import com.example.remote.services.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UtilitiesModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor {
                val original = it.request().url
                val url = original.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.IMDB_API_KEY)
                    .build()

                val requestBuilder = it.request().newBuilder()
                    .url(url)

                val request = requestBuilder.build()
                it.proceed(request)
            }
            .build())
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(retrofit: Retrofit): RetrofitClient = RetrofitClient(retrofit)
}