package com.mjpecora.app.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mjpecora.app.domain.repository.ApodRepository
import com.mjpecora.app.domain.repository.MarsRoverRepository
import com.mjpecora.app.network.BuildConfig.API_KEY
import com.mjpecora.app.network.BuildConfig.BASE_URL
import com.mjpecora.app.network.remote.NasaApi
import com.mjpecora.app.network.repository.ApodRepositoryImpl
import com.mjpecora.app.network.repository.MarsRoverRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun apodRepository(impl: ApodRepositoryImpl): ApodRepository

    @Binds
    @Singleton
    abstract fun marsRoverRepository(impl: MarsRoverRepositoryImpl): MarsRoverRepository

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .addInterceptor {
                    val originalRequest = it.request()
                    val originalUrl = originalRequest.url
                    val url = originalUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
                    val request = originalRequest.newBuilder().url(url).build()
                    it.proceed(request)
                }
                .build()

        @Provides
        @Singleton
        fun provideJson() = Json { ignoreUnknownKeys = true }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
            val contentType = "application/json".toMediaType()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
        }

        @Provides
        @Singleton
        fun provideNasaApi(retrofit: Retrofit): NasaApi = retrofit.create(NasaApi::class.java)
    }

}