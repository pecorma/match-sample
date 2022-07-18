package com.mjpecora.app.network.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsRoverResponse(val photos: List<PhotoResponse> = emptyList())

@Serializable
data class CameraResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("rover_id") val roverId: Int,
    @SerialName("full_name") val fullName: String,
)

@Serializable
data class PhotoResponse(
    @SerialName("id") val id: Int,
    @SerialName("sol") val sol: Int,
    @SerialName("camera") val camera: CameraResponse,
    @SerialName("img_src") val imgSrc: String,
    @SerialName("earth_date") val earthDate: String,
    @SerialName("rover") val rover: RoverResponse,
)

@Serializable
data class RoverResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("landing_date") val landingDate: String,
    @SerialName("launch_date") val launchDate: String,
    @SerialName("status") val status: String,
)