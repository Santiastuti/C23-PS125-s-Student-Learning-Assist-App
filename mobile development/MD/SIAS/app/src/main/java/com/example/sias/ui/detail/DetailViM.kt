package com.example.sias.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sias.datastore.response.Story
import com.example.sias.datastore.local.UserPreference

class DetailViM(private val pref: UserPreference) : ViewModel() {

    private val _story = MutableLiveData<Story>()
    val story: LiveData<Story> = _story
}