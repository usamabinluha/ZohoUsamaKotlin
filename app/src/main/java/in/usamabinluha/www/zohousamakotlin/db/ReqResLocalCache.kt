package `in`.usamabinluha.www.zohousamakotlin.db

import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.paging.DataSource
import android.util.Log
import java.util.concurrent.Executor


class ReqResLocalCache(
        private val userDao: UserDao,
        private val ioExecutor: Executor
) {

    fun insert(users: List<User>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("ReqResLocalCache", "inserting ${users.size} users")
            userDao.insert(users)
            insertFinished()
        }
    }

    fun users(): DataSource.Factory<Int, User> {
        return userDao.users()
    }
}