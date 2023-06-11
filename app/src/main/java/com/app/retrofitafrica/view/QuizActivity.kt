package com.app.retrofitafrica.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityQuizBinding



class QuizActivity : AppCompatActivity() {
    private var numPreguntas : Int = 0
    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numPreguntas = intent.getIntExtra("numPreguntas", 1)



    }
}