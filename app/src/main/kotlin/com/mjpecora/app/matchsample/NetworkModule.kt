package com.mjpecora.app.matchsample

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mjpecora.app.model.source.HomeDataSource
import com.mjpecora.app.networking.BuildConfig.API_KEY
import com.mjpecora.app.networking.BuildConfig.BASE_URL
import com.mjpecora.app.networking.mapper.HomeMapper
import com.mjpecora.app.networking.source.HomeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindHomeDataSource(dataSourceImpl: HomeDataSourceImpl): HomeDataSource

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
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