package com.example.sias.utils

import android.content.Context
import com.example.sias.datastore.UserPreference
import com.example.sias.datastore.api.ApiConfig
import com.example.sias.datastore.api.ApiSIASConfig
import com.example.sias.datastore.response.SIASRepository
import com.example.sias.datastore.response.StoryRepository

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val apiService = ApiConfig.getApiService()
        val pref = UserPreference(context)
        return StoryRepository(apiService, pref)
    }
    fun provideSIASRepository(): SIASRepository {
        val apiService = ApiSIASConfig.getApiService()
        return SIASRepository(apiService)
    }
}