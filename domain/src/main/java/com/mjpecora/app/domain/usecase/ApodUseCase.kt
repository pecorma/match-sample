package com.mjpecora.app.domain.usecase

import com.mjpecora.app.domain.repository.ApodRepository
import javax.inject.Inject

class ApodUseCase @Inject constructor(private val repository: ApodRepository) {
    suspend operator fun invoke() = repository.getApod()
}