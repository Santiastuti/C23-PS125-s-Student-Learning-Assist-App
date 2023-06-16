package com.example.sias.ui.submit

import androidx.lifecycle.ViewModel
import com.example.sias.datastore.response.SIASRepository

class SubmitActivityViewModel(private val siasRepository: SIASRepository) : ViewModel() {
    // Tambahkan logika ViewModel jika diperlukan
    fun predictText(text: String)=siasRepository.predictText(text)
}
