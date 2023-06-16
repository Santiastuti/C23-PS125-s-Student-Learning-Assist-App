package com.example.sias.datastore.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            return getApiService("story-api")
        }

        fun getApiService(apiType: String): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofitStoryApi = Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val retrofitApiApp = Retrofit.Builder()
                .baseUrl("https://api-app-mrsltgtoaa-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return if (apiType == "story-api") {
                retrofitStoryApi.create(ApiService::class.java)
            } else {
                retrofitApiApp.create(ApiService::class.java)
            }
        }
    }
}
