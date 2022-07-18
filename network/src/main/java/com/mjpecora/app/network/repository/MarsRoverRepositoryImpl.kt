package com.mjpecora.app.network.repository

import com.mjpecora.app.domain.model.MarsRover
import com.mjpecora.app.domain.repository.MarsRoverRepository
import com.mjpecora.app.network.mapper.MarsRoverResponseToMarsRoverDomainMapper
import com.mjpecora.app.network.remote.NasaApi
import javax.inject.Inject

class MarsRoverRepositoryImpl @Inject constructor(
    private val api: NasaApi,
    private val mapper: MarsRoverResponseToMarsRoverDomainMapper = MarsRoverResponseToMarsRoverDomainMapper()
) : MarsRoverRepository {
    override suspend fun getMarsRoverPhotos(sol: Int, page: Int): List<MarsRover> {
        return mapper.invoke(api.getMarsPhotos(sol, page))
    }
}