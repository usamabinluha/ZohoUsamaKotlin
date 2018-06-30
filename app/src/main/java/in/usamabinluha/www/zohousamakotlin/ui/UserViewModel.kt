package `in`.usamabinluha.www.zohousamakotlin.ui

import `in`.usamabinluha.www.zohousamakotlin.data.ReqResRepository
import `in`.usamabinluha.www.zohousamakotlin.model.FetchUsersResult
import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList

class UserViewModel(private val repository: ReqResRepository) : ViewModel() {


    private val queryLiveData = MutableLiveData<String>()
    private val userResult: LiveData<FetchUsersResult> = Transformations.map(queryLiveData, {
        repository.search()
    })

    val users: LiveData<PagedList<User>> = Transformations.switchMap(userResult,
            { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(userResult,
            { it -> it.networkErrors })

    fun searchUser() {
        queryLiveData.postValue("Android")
    }

}