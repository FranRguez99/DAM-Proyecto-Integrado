package com.app.retrofitafrica

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityMainBinding
import com.app.retrofitafrica.databinding.LayoutLogInBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var logInBinding : LayoutLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btOpenLogIn.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this)
            logInBinding = LayoutLogInBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(logInBinding.root)
            // Toda funcionalidad del login deberá estar antes del show, para llamar a
            // los elementos de la vista utilizar el logInBinding

            bottomSheetDialog.show()

        }

    }

    /*private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://africapp-258b7-default-rtdb.europe-west1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/


}