package `in`.usamabinluha.www.zohousamakotlin.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

data class FetchUsersResult(
        val data: LiveData<PagedList<User>>,
        val networkErrors: LiveData<String>
)