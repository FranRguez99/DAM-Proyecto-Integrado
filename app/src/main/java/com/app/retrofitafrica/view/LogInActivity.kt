package com.app.retrofitafrica.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.retrofitafrica.databinding.ActivityLogInBinding
import com.app.retrofitafrica.viewmodel.AuthController

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var authController: AuthController
    private  var email : String = String()
    private  var pass : String = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authController = ViewModelProvider(this)[AuthController::class.java]
        authController.loginResultLiveData.observe(this) { logIn ->
            if (logIn != null) {
                email = binding.tietMail.text.toString()
                pass = binding.tietPass.text.toString()
                val preferences = getSharedPreferences("remember", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preferences.edit()
                if (binding.switch1.isChecked) {
                    editor.putString("email", email)
                    editor.putString("pass", pass)
                    editor.apply()
                } else {
                    editor.putString("email", "")
                    editor.putString("pass", "")
                    editor.apply()
                }
                mostrarToast("Sesión iniciada exitosamente")
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                mostrarToast("Usuario o contraseña incorrectos")
            }
        }
        binding.btLogIn.setOnClickListener {
            if (!binding.tietMail.text.isNullOrEmpty() || !binding.tietPass.text.isNullOrEmpty()) {
                email = binding.tietMail.text.toString()
                pass = binding.tietPass.text.toString()
                authController.login(email, pass)
            } else {
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btSignIn.setOnClickListener {
            startActivity(Intent(this , SignInActivity::class.java))
        }
        getSharedRemember()
    }

    private fun mostrarToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getSharedRemember() {
        val preferences = getSharedPreferences("remember", MODE_PRIVATE)
        binding.tietMail.setText(preferences.getString("email", "").toString())
        binding.tietPass.setText(preferences.getString("pass", "").toString())
        if (!binding.tietMail.text.isNullOrEmpty()) {
            binding.switch1.isChecked = true
        }

    }

}


