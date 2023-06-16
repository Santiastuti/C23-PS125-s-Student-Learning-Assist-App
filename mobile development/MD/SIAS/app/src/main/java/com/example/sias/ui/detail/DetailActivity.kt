package com.example.sias.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.sias.ui.VMFactory
import com.example.sias.datastore.response.ListStoryItem
import com.example.sias.datastore.local.UserPreference
import com.example.sias.databinding.ActivityDetailBinding
import com.example.sias.ui.answer.AnswerVM

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class  DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViM: AnswerVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViM = ViewModelProvider(
            this,
            VMFactory(UserPreference.getInstance(dataStore))
        )[AnswerVM::class.java]

        val story = intent.getParcelableExtra<ListStoryItem>("STORY")
        binding.apply {
            Glide.with(this@DetailActivity).load(story?.photoUrl).into(ivDetailPhoto)
            tvDetailName.text = story?.name
            tvDetailDescription.text = story?.description
        }
    }
}