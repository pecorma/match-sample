package com.mjpecora.app.network.remote

import com.mjpecora.app.network.remote.response.ApodResponse
import com.mjpecora.app.network.remote.response.MarsRoverResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApi {
    @GET("planetary/apod")
    suspend fun getApod(): ApodResponse

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsPhotos(
        @Query("sol") sol: Int,
        @Query("page") page: Int = 1
    ): MarsRoverResponse

}