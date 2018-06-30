package `in`.usamabinluha.www.zohousamakotlin.data

import `in`.usamabinluha.www.zohousamakotlin.api.ReqResService
import `in`.usamabinluha.www.zohousamakotlin.db.ReqResLocalCache
import `in`.usamabinluha.www.zohousamakotlin.model.FetchUsersResult
import android.arch.paging.LivePagedListBuilder

class ReqResRepository(
        private val service: ReqResService,
        private val cache: ReqResLocalCache
) {

    fun search(): FetchUsersResult {

        // Get data from the local cache
        val dataSourceFactory  = cache.users()
        val boundaryCallback = UserBoundaryCallback(service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()
        return FetchUsersResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 1
    }
}