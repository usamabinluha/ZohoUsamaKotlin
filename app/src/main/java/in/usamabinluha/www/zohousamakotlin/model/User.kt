package `in`.usamabinluha.www.zohousamakotlin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
        @PrimaryKey @field:SerializedName("id") val id: Int,
        @field:SerializedName("first_name") val first_name: String,
        @field:SerializedName("last_name") val last_name: String,
        @field:SerializedName("avatar") val avatar: String?
)
