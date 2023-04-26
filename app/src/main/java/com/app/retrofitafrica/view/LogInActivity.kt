package com.app.retrofitafrica.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityLogInBinding
import com.app.retrofitafrica.viewmodel.AuthController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var authController: AuthController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authController = AuthController(this)
        getSharedRemember()
        binding.btLogIn.setOnClickListener {
            if (!binding.tietMail.text.isNullOrEmpty() || !binding.tietPass.text.isNullOrEmpty()) {
                val email = binding.tietMail.text.toString()
                val pass = binding.tietPass.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    authController.login(email, pass, binding.switch1.isChecked)
                }
                // LifecycleScope
            } else {
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }
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