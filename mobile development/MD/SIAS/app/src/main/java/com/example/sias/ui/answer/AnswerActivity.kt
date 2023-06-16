package com.example.sias.ui.answer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sias.R
import com.example.sias.databinding.ActivityAnswerBinding
import com.example.sias.ui.main.MainActivity
import com.example.sias.ui.submit.SubmitActivity

class AnswerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value = intent.getStringExtra("predict")
        binding.tipeBelajar.text = value

        val deskripsi = when (value?.toLowerCase()) {
            "visual" -> getString(R.string.deskripsi_visual)
            "auditory" -> getString(R.string.deskripsi_auditory)
            "kinestetik" -> getString(R.string.deskripsi_kinestetik)
            else -> ""
        }
        binding.tipeDeskripsi.text = deskripsi

        val retakeButton = binding.btnRetake
        retakeButton.setOnClickListener {
            // Kode untuk kembali ke SubmitActivity saat tombol "btn_retake" ditekan
            val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
            finish()
        }

        val kembaliButton = binding.btnKembali
        kembaliButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
