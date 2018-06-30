package `in`.usamabinluha.www.zohousamakotlin.api


import `in`.usamabinluha.www.zohousamakotlin.model.User
import com.google.gson.annotations.SerializedName

data class FetchUsersResponse(
        @SerializedName("page") val page: Int = 0,
        @SerializedName("per_page") val per_page: Int = 0,
        @SerializedName("total") val total: Int = 0,
        @SerializedName("total_pages") val total_pages: Int = 0,
        @SerializedName("data") val data: List<User> = emptyList(),
        val nextPage: Int? = null
)
