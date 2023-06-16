package com.example.sias.datastore.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSIASConfig {
    companion object{
        fun getApiService(): ApiSIASService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofitApiApp = Retrofit.Builder()
                .baseUrl("https://api-app-mrsltgtoaa-et.a.run.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofitApiApp.create(ApiSIASService::class.java)
        }
    }
}
