package com.app.retrofitafrica.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.retrofitafrica.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackButtonMenu.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        binding.btGlosary.setOnClickListener {
            startActivity(Intent(this, GlossaryActivity::class.java))
        }
    }
}