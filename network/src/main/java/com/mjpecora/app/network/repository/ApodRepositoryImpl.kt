package com.mjpecora.app.network.repository

import com.mjpecora.app.domain.model.Apod
import com.mjpecora.app.domain.repository.ApodRepository
import com.mjpecora.app.network.mapper.ApodResponseToDomainApodMapper
import com.mjpecora.app.network.remote.NasaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
    private val api: NasaApi,
    private val mapper: ApodResponseToDomainApodMapper = ApodResponseToDomainApodMapper()
): ApodRepository {
    override suspend fun getApod() = getApodFromRemote()

    private suspend fun getApodFromRemote(): Apod {
        return withContext(Dispatchers.IO) {
            mapper.invoke(api.getApod())
        }
    }
}