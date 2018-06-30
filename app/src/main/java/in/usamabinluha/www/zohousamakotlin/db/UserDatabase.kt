package `in`.usamabinluha.www.zohousamakotlin.db

import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = [User::class],
        version = 1,
        exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        UserDatabase::class.java, "ReqRes.db")
                        .build()
    }
}