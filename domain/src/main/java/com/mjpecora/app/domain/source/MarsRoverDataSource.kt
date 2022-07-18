package com.mjpecora.app.domain.source

import androidx.paging.PagingData
import com.mjpecora.app.domain.model.MarsRover
import kotlinx.coroutines.flow.Flow

interface MarsRoverDataSource {
    fun getPhotos(): Flow<PagingData<MarsRover>>
}