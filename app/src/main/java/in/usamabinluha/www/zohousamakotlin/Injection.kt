package `in`.usamabinluha.www.zohousamakotlin

import `in`.usamabinluha.www.zohousamakotlin.api.ReqResService
import `in`.usamabinluha.www.zohousamakotlin.data.ReqResRepository
import `in`.usamabinluha.www.zohousamakotlin.db.ReqResLocalCache
import `in`.usamabinluha.www.zohousamakotlin.db.UserDatabase
import `in`.usamabinluha.www.zohousamakotlin.ui.ViewModelFactory
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.util.concurrent.Executors

object Injection {


    private fun provideCache(context: Context): ReqResLocalCache {
        val database = UserDatabase.getInstance(context)
        return ReqResLocalCache(database.usersDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideReqResRepository(context: Context): ReqResRepository {
        return ReqResRepository(ReqResService.create(), provideCache(context))
    }


    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideReqResRepository(context))
    }

}