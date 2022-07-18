package com.mjpecora.app.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mjpecora.app.domain.model.MarsRover
import com.mjpecora.app.domain.source.MarsRoverPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MarsRoverUseCase @Inject constructor(
    private val pagingSource: MarsRoverPagingSource
) {
    operator fun invoke(): Flow<PagingData<MarsRover>> =
        Pager(PagingConfig(pageSize = 20)) { pagingSource }.flow
}