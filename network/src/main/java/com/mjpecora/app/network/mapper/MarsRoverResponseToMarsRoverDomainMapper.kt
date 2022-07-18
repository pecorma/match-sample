package com.mjpecora.app.network.mapper

import com.mjpecora.app.domain.model.MarsRover
import com.mjpecora.app.network.remote.response.MarsRoverResponse
import javax.inject.Inject

class MarsRoverResponseToMarsRoverDomainMapper @Inject constructor() {
    operator fun invoke(response: MarsRoverResponse): List<MarsRover> {
        return response.photos.map {
            MarsRover(
                imgSrc = it.imgSrc,
                roverName = it.rover.name,
                date = it.earthDate,
                sol = it.sol
            )
        }
    }
}