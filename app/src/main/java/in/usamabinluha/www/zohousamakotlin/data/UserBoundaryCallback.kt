package `in`.usamabinluha.www.zohousamakotlin.data

import `in`.usamabinluha.www.zohousamakotlin.api.ReqResService
import `in`.usamabinluha.www.zohousamakotlin.api.fetchUsers
import `in`.usamabinluha.www.zohousamakotlin.db.ReqResLocalCache
import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log

class UserBoundaryCallback(private val service: ReqResService,
                           private val cache: ReqResLocalCache): PagedList.BoundaryCallback<User>() {

    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    val networkErrors: LiveData<String>
        get() = _networkErrors

    private var isRequestInProgress = false


    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        saveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: User) {
        Log.d("RepoBoundaryCallback", "onItemAtEndLoaded")
        saveData()
    }

    private fun saveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        fetchUsers(service,  lastRequestedPage, { users ->
            cache.insert(users, {
                lastRequestedPage++
                isRequestInProgress = false
            })
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}