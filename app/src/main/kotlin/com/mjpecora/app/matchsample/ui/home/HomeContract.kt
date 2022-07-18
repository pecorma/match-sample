package com.mjpecora.app.matchsample.ui.home

import androidx.paging.PagingData
import com.mjpecora.app.domain.model.Apod
import com.mjpecora.app.domain.model.MarsRover
import kotlinx.coroutines.flow.Flow

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Loaded(
        val apodData: Apod? = null,
        val marsRoverFlow: Flow<PagingData<MarsRover>>? = null
    ): HomeUiState()
    object Error : HomeUiState()
}