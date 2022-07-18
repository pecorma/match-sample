package com.mjpecora.app.domain.repository

import com.mjpecora.app.domain.model.MarsRover

interface MarsRoverRepository {
    suspend fun getMarsRoverPhotos(sol: Int, page: Int = 1): List<MarsRover>
}