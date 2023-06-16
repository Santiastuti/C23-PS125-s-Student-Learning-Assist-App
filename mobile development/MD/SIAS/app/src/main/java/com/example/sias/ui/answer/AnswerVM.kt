package com.example.sias.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sias.datastore.response.Story
import com.example.sias.datastore.local.UserPreference

class AnswerVM(private val pref: UserPreference) : ViewModel() {

    private val _story = MutableLiveData<Story>()
    val story: LiveData<Story> = _story
}