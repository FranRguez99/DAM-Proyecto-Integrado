package com.app.retrofitafrica.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityLogInBinding
import com.app.retrofitafrica.viewmodel.LogInController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    private lateinit var logInController: LogInController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logInController = LogInController(this)

        binding.btLogIn.setOnClickListener {
            val email = binding.tietMail.text.toString()
            val pass = binding.tietPass.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                logInController.login(email, pass)
            }
        }
    }

}