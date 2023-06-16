package com.example.sias.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.sias.datastore.local.UserPreference
import com.example.sias.ui.login.LoginVM
import com.example.sias.ui.main.MainActivity
import com.example.sias.databinding.ActivitySplashBinding
import com.example.sias.ui.login.LoginActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SSActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var loginVM: LoginVM
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menghilangkan status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginVM = ViewModelProvider(
            this,
            VMFactory(UserPreference.getInstance(dataStore))
        ).get(LoginVM::class.java)

        loginVM.stateData().observe(this) {
            token = it
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("TOKEN", token)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2000)
    }
}
