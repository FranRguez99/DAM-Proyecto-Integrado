package com.app.retrofitafrica

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val displayMetrics = resources.displayMetrics
        val dpHeight = displayMetrics.heightPixels


        var layoutParams = binding.loginscreen.layoutParams
        layoutParams.height = dpHeight - 200
        binding.loginscreen.layoutParams = layoutParams

        layoutParams = binding.imageView.layoutParams
        layoutParams.height = dpHeight - 350
        binding.imageView.layoutParams = layoutParams



    }

    /*private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://africapp-258b7-default-rtdb.europe-west1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/


}