package com.app.retrofitafrica.view

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityTestBinding
import com.app.retrofitafrica.viewmodel.AuthController

class TestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBinding
    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Seekbar
        binding.sbNumber.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val number = progress + 1
                binding.tvQuestions.text = number.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example, but you can add any necessary logic here
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used in this example, but you can add any necessary logic here
            }
        })

    }



}