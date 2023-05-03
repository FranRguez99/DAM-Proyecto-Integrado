package com.app.retrofitafrica.viewmodel


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.app.retrofitafrica.view.LogInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthController(private var context: Context) {
    private lateinit var firebaseAuth : FirebaseAuth

    @SuppressLint("CommitPrefEdits")
    fun login(email : String, password : String, remember : Boolean) {
            firebaseAuth = Firebase.auth
            firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mostrarToast("Usuario Logeado")
                    mostrarToast("Mostrar menú")
                    //Aqui se debe modificar
                    //val intent = Intent(context, ::class.java )
                    //context.startActivity(intent)
                    val preferences = context.getSharedPreferences("remember", MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = preferences.edit()

                    if (remember) {
                        editor.putString("email", email)
                        editor.putString("pass", password)
                        editor.apply()
                    } else {
                        editor.putString("email", "")
                        editor.putString("pass", "")
                        editor.apply()
                    }

                } else {
                    mostrarToast("Usuario o contraseña incorrectos")
                }
            }
    }

    fun signIn(email: String, password: String) {
        firebaseAuth = Firebase.auth
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                context.startActivity(Intent(context, LogInActivity::class.java))

            } else {
                mostrarToast("Ha ocurrido un error, vuelva a intentarlo más tarde")
            }
        }
    }

    private fun mostrarToast(text : String ) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
