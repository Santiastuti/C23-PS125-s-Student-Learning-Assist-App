package com.example.sias.ui.home

import android.content.Context
import androidx.lifecycle.*
import androidx.paging.*
import com.example.sias.datastore.response.ListStoryItem
import com.example.sias.utils.Injection

class HomeViewModel(context: Context) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    val stories : LiveData<PagingData<ListStoryItem>> =
        Injection.provideRepository(context)
            .getAllStories()
            .cachedIn(viewModelScope)

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(context) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}

