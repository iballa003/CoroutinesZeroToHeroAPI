package org.iesharia.coroutineszerotohero

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("/users")
    suspend fun getUsers(): Response<List<UserItemResponse>>
}


data class UserItemResponse(
    @SerializedName("id") val id:String,
    @SerializedName("name") val name:String,
    @SerializedName("username") val username:String,
    @SerializedName("email") val email:String
)