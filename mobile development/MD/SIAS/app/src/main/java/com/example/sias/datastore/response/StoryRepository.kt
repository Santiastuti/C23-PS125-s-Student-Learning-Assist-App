package com.example.sias.datastore.response

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.sias.datastore.StoryPagingSource
import com.example.sias.datastore.UserPreference
import com.example.sias.datastore.api.ApiService


class StoryRepository(private val apiService: ApiService, private val pref: UserPreference) {

    val token = pref.getToken()

    fun getAllStories(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService, token.toString())
            }
        ).liveData
    }
}