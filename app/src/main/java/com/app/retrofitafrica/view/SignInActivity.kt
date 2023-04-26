package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivitySignInBinding
import com.app.retrofitafrica.viewmodel.AuthController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var authController: AuthController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authController = AuthController(this)
        binding.ivBackButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        binding.btSignIn.setOnClickListener {
            if ( !binding.tietPass.text.isNullOrEmpty() || !binding.tietConfPass.text.isNullOrEmpty() || !binding.tietSignMail.text.isNullOrEmpty()  ) {
                if (binding.tietPass.text.toString() == binding.tietConfPass.text.toString()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        authController.signIn(binding.tietSignMail.text.toString(), binding.tietPass.text.toString())
                    }

                } else {
                    mostrarToast("Las contraseñas no coinciden")
                }
            } else {
                mostrarToast("Debe rellenar todos los campos")
            }

        }


    }
    private fun mostrarToast(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}