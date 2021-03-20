package com.danielefavaro.spacex.main.di.module

import com.danielefavaro.spacex.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesSpaceXRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BuildConfig.SPACEX_BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build()

    @Provides
    @Singleton
    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }.build()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}