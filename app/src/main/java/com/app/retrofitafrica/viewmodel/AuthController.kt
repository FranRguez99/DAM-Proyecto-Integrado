package com.app.retrofitafrica.viewmodel


import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthController : ViewModel() {
    private val firebaseAuth : FirebaseAuth = Firebase.auth
    val loginResultLiveData : MutableLiveData<FirebaseUser?> = MutableLiveData()
    val signInResultLiveData : MutableLiveData<FirebaseUser?> = MutableLiveData()

    @SuppressLint("CommitPrefEdits")
    fun login(email : String, password : String) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val loginResult = firebaseAuth.signInWithEmailAndPassword(email , password).await()
                    val user : FirebaseUser? = loginResult.user
                    withContext(Dispatchers.Main) {
                        loginResultLiveData.value = user
                    }
                } catch (e : Exception) {
                    withContext(Dispatchers.Main) {
                        loginResultLiveData.value = null
                    }
                }

            }
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val signInResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val user = signInResult.user
                withContext(Dispatchers.Main) {
                    signInResultLiveData.value = user
                }

            } catch (e : Exception) {
                signInResultLiveData.value = null
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        firebaseAuth.signOut()
    }
}
