package com.example.sias.datastore.response

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.sias.datastore.StoryPagingSource
import com.example.sias.datastore.UserPreference
import com.example.sias.datastore.api.ApiSIASService
import com.example.sias.datastore.api.ApiService
import com.example.sias.datastore.api.PredictBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SIASRepository(private val apiService: ApiSIASService) {
    private var result = MutableLiveData<String>()
    fun predictText(text: String): LiveData<String> {
        val client = apiService.predictText(PredictBody(text))
        client.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    result.value = response.body().toString()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                result.value = t.message.toString()
            }
        })
        return result
    }
}