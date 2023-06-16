package com.example.sias.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.sias.ui.VMFactory
import com.example.myapplication.datastore.response.LoginResult
import com.example.sias.datastore.local.UserPreference
import com.example.sias.ui.register.RegisterActivity
import com.example.sias.ui.main.MainActivity
import com.example.sias.databinding.ActivityLoginBinding

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginVM: LoginVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginVM = ViewModelProvider(
            this,
            VMFactory(UserPreference.getInstance(dataStore))
        )[LoginVM::class.java]

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                if (password.length >= 8) {
                    loginVM.login(
                        email, password
                    )
                    loginVM.data.observe(this){ data ->
                        saveKey(data)
                    }
                }
            } else {
                toastMessage("Fill the form")
            }
        }

        loginVM.message.observe(this) { message ->
            toastMessage(message)
            if (message == "success") {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

        loginVM.loading.observe(this) { loading ->
            showLoading(loading)
        }
    }

    private fun saveKey(data: LoginResult?) {
        val pref = com.example.sias.datastore.UserPreference(applicationContext)
        pref.setToken(data?.token.toString())
    }

    private fun toastMessage(message: String) {
        val changeMessage: String
        if (message == "Bad Request" || message == "Unauthorized") {
            changeMessage = "Email or Password Invalid"
            Toast.makeText(this, changeMessage, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}