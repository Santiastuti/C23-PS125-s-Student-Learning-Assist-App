package com.example.sias.datastore.api


import retrofit2.Call
import retrofit2.http.*

interface ApiSIASService {
    @POST("predict_text")
    fun predictText(
        @Body predictBody: PredictBody
    ): Call<String>
}
data class PredictBody(
    val text: String
)