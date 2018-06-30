package `in`.usamabinluha.www.zohousamakotlin.db

import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<User>)

    @Query("SELECT * FROM users")
    fun users(): DataSource.Factory<Int, User>

}