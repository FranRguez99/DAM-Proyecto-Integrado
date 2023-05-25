package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityMainBinding
import com.app.retrofitafrica.viewmodel.RetrofitRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val retrofitRoom = RetrofitRoom()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            retrofitRoom.apiToRoom(context)
        }




        binding.btOpenLogIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }






}