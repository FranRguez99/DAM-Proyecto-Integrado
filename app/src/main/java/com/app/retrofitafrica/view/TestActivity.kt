package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityTestBinding
import com.app.retrofitafrica.viewmodel.DBPaises

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private lateinit var dbPaises: DBPaises

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbPaises = DBPaises(this)

        dbPaises.getCountPaisesLiveData().observe(this) {numPaises ->
            numPaises?.let { number ->
                if (number >= 1 ) {
                    binding.sbNumber.max = number
                }
            }
        }
        dbPaises.cargarNumPaises()


        // Seekbar
        binding.sbNumber.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val number = progress + 1
                binding.tvQuestions.text = number.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        binding.btStart.setOnClickListener {
            val numPreguntas = binding.sbNumber.progress
            startActivity(Intent(this, QuizActivity::class.java).putExtra("numPreguntas", numPreguntas))
        }

        binding.ivBackButtonSelectTest.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

    }



}