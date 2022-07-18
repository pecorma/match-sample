package com.mjpecora.app.network.mapper

import com.mjpecora.app.domain.model.Apod
import com.mjpecora.app.network.remote.response.ApodResponse
import javax.inject.Inject

class ApodResponseToDomainApodMapper @Inject constructor() {
    operator fun invoke(response: ApodResponse): Apod {
        return Apod(
            title = response.title ?: "No title",
            date = response.date ?: "UNKNOWN",
            copyright = response.copyright ?: "UNKNOWN",
            url = response.url ?: ""
        )
    }

}