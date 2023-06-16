package com.example.sias.datastore.api


import com.example.myapplication.datastore.response.LoginResponse
import com.example.sias.datastore.response.GeneralResponse
import com.example.sias.datastore.response.StoriesResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") Name: String,
        @Field("email") Email: String,
        @Field("password") Password: String
    ): Call<GeneralResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") Email: String,
        @Field("password") Password: String
    ): Call<LoginResponse>

    @GET("stories")
    suspend fun getAllStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): StoriesResponse

}