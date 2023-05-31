package com.app.retrofitafrica.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.retrofitafrica.databinding.ActivityTestBinding
import com.app.retrofitafrica.viewmodel.AuthController

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}