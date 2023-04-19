package com.app.retrofitafrica.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}