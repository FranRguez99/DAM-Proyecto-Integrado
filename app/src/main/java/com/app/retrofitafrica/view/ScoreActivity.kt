package com.app.retrofitafrica.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityScoreBinding
import kotlin.math.roundToInt

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val numPreguntas = intent.getIntExtra("numPreguntas", 0)
        val numAciertos = intent.getIntExtra("numAciertos", 0)
        val numFallos = intent.getIntExtra("numFallos", 0)
        binding.tvNumPreguntasScore.text = numPreguntas.toString()
        binding.tvNumAciertos.text = numAciertos.toString()
        binding.tvNumFallos.text = numFallos.toString()
        binding.tvPercentAciertos.text = ((numAciertos * 100.0 / numPreguntas).roundToInt().toString()) + "%"


        binding.btVolverMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java ))
        }
    }
}

