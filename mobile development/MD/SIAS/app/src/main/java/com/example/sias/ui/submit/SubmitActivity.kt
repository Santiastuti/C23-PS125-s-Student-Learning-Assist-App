package com.example.sias.ui.submit


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sias.R
import com.example.sias.ui.SIASViewModelFactory
import com.example.sias.ui.answer.AnswerActivity


class SubmitActivity : AppCompatActivity() {

    private lateinit var edJawaban: EditText
    private lateinit var btnSubmit: Button
    private lateinit var pbLoadingScreen: ProgressBar
    private val submitActivityViewModel: SubmitActivityViewModel by viewModels {
        SIASViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        edJawaban = findViewById(R.id.ed_jawaban)
        btnSubmit = findViewById(R.id.btn_submit)
        pbLoadingScreen = findViewById(R.id.pb_loading_screen)

        // Menambahkan border ke EditText
        val shapeDrawable = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.WHITE) // Warna background
            setStroke(1, Color.parseColor("#78909C")) // Warna border dan lebar border
            cornerRadius = 4f // Radius sudut border
        }
        edJawaban.background = shapeDrawable

        btnSubmit.setOnClickListener {
            val jawaban = edJawaban.text.toString().trim()

            if (jawaban.isNotEmpty()) {
                // Jika jawaban tidak kosong, lakukan pengiriman data atau tindakan yang diperlukan di sini
                pbLoadingScreen.visibility = View.VISIBLE
                submitActivityViewModel.predictText(jawaban).observe(this) {
                    // Contoh pindah ke AnswerActivity
                    val intent = Intent(this, AnswerActivity::class.java)
                    intent.putExtra("predict", it)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Jawaban tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
