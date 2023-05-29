package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.retrofitafrica.databinding.ActivitySignInBinding
import com.app.retrofitafrica.viewmodel.AuthController

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var authController: AuthController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authController = ViewModelProvider(this)[AuthController::class.java]
        binding.ivBackButton.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }
        authController.signInResultLiveData.observe(this) { signIn ->
            if (signIn != null) {
                startActivity(Intent(this, LogInActivity::class.java))
                mostrarToast("Usuario registrado con exito")
            }

        }

        binding.btSignIn.setOnClickListener {
            if ( !binding.tietPass.text.isNullOrEmpty() || !binding.tietConfPass.text.isNullOrEmpty() || !binding.tietSignMail.text.isNullOrEmpty()  ) {
                if (binding.tietPass.text.toString() == binding.tietConfPass.text.toString()) {
                    val email = binding.tietSignMail.text.toString()
                    val pass = binding.tietPass.text.toString()
                    authController.signIn(email, pass)

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