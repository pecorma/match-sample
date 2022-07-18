package com.mjpecora.app.domain.repository

import com.mjpecora.app.domain.model.Apod

interface ApodRepository {
    suspend fun getApod(): Apod
}