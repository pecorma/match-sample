package com.mjpecora.app.domain.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mjpecora.app.domain.model.MarsRover
import com.mjpecora.app.domain.repository.MarsRoverRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.random.Random

class MarsRoverPagingSource @Inject constructor(
    private val repository: MarsRoverRepository,
) : PagingSource<Int, MarsRover>() {

    override fun getRefreshKey(state: PagingState<Int, MarsRover>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarsRover> {
        return try {
            val nextPage = params.key ?: 1
            val photos = repository.getMarsRoverPhotos(Random.nextInt(0, 3400), 1)
            LoadResult.Page(
                data = photos,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (photos.isEmpty()) 1 else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}