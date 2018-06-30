package `in`.usamabinluha.www.zohousamakotlin.api

import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val TAG = "ReqResService"

fun fetchUsers(
        service: ReqResService,
        page: Int,
        onSuccess: (users: List<User>) -> Unit,
        onError: (error: String) -> Unit) {
    Log.d(TAG,  "page: $page")

    service.fetchUsers(page).enqueue(
            object : Callback<FetchUsersResponse> {
                override fun onFailure(call: Call<FetchUsersResponse>?, t: Throwable) {
                    Log.d(TAG, "fail to get data")
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(
                        call: Call<FetchUsersResponse>?,
                        response: Response<FetchUsersResponse>
                ) {
                    Log.d(TAG, "got a response $response")
                    Log.d(TAG, "response body ${response.body()?.data ?: emptyList()}")
                    if (response.isSuccessful) {
                        val users = response.body()?.data ?: emptyList()
                        onSuccess(users)
                    } else {
                        onError(response.errorBody()?.string() ?: "Unknown error")
                    }
                }
            }
    )
}


interface ReqResService {

    @GET("users")
    fun fetchUsers(@Query("page") page: Int): Call<FetchUsersResponse>


    companion object {
        private const val BASE_URL = "https://reqres.in/api/"

        fun create(): ReqResService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ReqResService::class.java)
        }
    }
}