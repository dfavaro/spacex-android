package com.danielefavaro.spacex.main.di.module

import com.danielefavaro.spacex.base.util.UtilsTest
import com.danielefavaro.spacex.data.service.SpaceXService
import dagger.Module
import dagger.Provides
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val REQUEST_PAST_LAUNCHES = "launches/past"

private const val RESPONSE_PAST_LAUNCHES = "getPastLaunches"

@Module
class NetworkModuleTest {

    @Singleton
    @Provides
    fun providesSpaceXRetrofit(retrofit: Retrofit) = retrofit.create(SpaceXService::class.java)

    @Singleton
    @Provides
    fun providesMockWebServer() = MockWebServer()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, mockWebServer: MockWebServer) =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url(("/")))
            .build()

    @Singleton
    @Provides
    fun providesHttpClient() = OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                // custom logic
                uri.contains(REQUEST_PAST_LAUNCHES) -> UtilsTest.getJsonFromFile("$RESPONSE_PAST_LAUNCHES.json")
                else -> ""
            }

            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message(responseString)
                .addHeader("content-type", "application/json")
                .body(responseString.toResponseBody("application/json; charset=utf-8".toMediaType()))
                .build()
        }
        .build()

}