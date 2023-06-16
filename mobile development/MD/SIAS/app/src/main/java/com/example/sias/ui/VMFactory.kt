package com.example.sias.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sias.datastore.local.UserPreference
import com.example.sias.datastore.response.SIASRepository
import com.example.sias.ui.answer.AnswerVM
import com.example.sias.ui.home.HomeViewModel
import com.example.sias.ui.login.LoginVM
import com.example.sias.ui.submit.SubmitActivityViewModel
import com.example.sias.utils.Injection


class VMFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginVM::class.java)-> {
                LoginVM(pref) as T
            }
            modelClass.isAssignableFrom(AnswerVM::class.java)->{
                AnswerVM(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}

class SIASViewModelFactory private constructor(private val siasRepository: SIASRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubmitActivityViewModel::class.java)) {
            return SubmitActivityViewModel(siasRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: SIASViewModelFactory? = null
        fun getInstance(): SIASViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: SIASViewModelFactory(Injection.provideSIASRepository())
            }.also { instance = it }
    }
}