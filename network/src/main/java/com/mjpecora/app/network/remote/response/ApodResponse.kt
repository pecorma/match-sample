package com.mjpecora.app.network.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApodResponse(
    @SerialName("copyright") val copyright: String? = null,
    @SerialName("date") val date: String? = null,
    @SerialName("explanation") val explanation: String? = null,
    @SerialName("hdurl") val hdurl: String? = null,
    @SerialName("media_type") val mediaType: String? = null,
    @SerialName("service_version") val version: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("url") val url: String? = null,
)


//{
//    "copyright": "Bertrand Kulik",
//    "date": "2022-07-12",
//    "explanation": "It's northern noctilucent cloud season. Composed of small ice crystals forming only during specific conditions in the upper atmosphere, noctilucent clouds may become visible at sunset during late summer when illuminated by sunlight from below.  Noctilucent clouds are the highest clouds known and now established to be polar mesospheric clouds observed from the ground.  Although observed with NASA's AIM satellite since 2007, much about noctilucent clouds remains unknown and so a topic of active research. The featured image shows expansive and rippled noctilucent clouds wafting over Paris, France. This year, several northern locations are already reporting especially vivid displays of noctilucent clouds.",
//    "hdurl": "https://apod.nasa.gov/apod/image/2207/NoctilucentParis_Kulik_1080.jpg",
//    "media_type": "image",
//    "service_version": "v1",
//    "title": "Noctilucent Clouds over Paris",
//    "url": "https://apod.nasa.gov/apod/image/2207/NoctilucentParis_Kulik_1080.jpg"
//}