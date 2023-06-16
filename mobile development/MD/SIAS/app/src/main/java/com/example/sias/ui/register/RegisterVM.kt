package com.example.myapplication.ui.register

import androidx.lifecycle.*
import com.example.sias.datastore.api.ApiConfig
import com.example.sias.datastore.response.GeneralResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVM : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val client = ApiConfig.getApiService()

    fun register(name: String, email: String, password: String){
        _loading.value = true
        client.register(name, email, password).enqueue(object : Callback<GeneralResponse> {
            override fun onResponse(
                call: Call<GeneralResponse>,
                response: Response<GeneralResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _message.value = response.body()?.message
                }else{
                    _message.value = response.message()
                }
            }
            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                _loading.value = false
                _message.value = t.message
            }
        })
    }
}