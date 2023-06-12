package com.app.retrofitafrica.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.retrofitafrica.databinding.ActivityQuizBinding
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.QuizController
import com.squareup.picasso.Picasso


class QuizActivity : AppCompatActivity() {
    private var numPreguntas : Int = 0
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quizController: QuizController
    private lateinit var currentQuestionOptions: List<Pais>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numPreguntas = intent.getIntExtra("numPreguntas", 1)

        // Create an instance of QuizController
        quizController = QuizController(this)

        // Load the first question
        loadQuestion()

        // Set click listener for the "Continue" button
        binding.btStart2.setOnClickListener {
            // Load the next question when the button is clicked
            loadQuestion()
        }
    }

    private fun loadQuestion() {
        // Get the next question options from the QuizController
        currentQuestionOptions = quizController.getNextQuestion()

        // Display the flag image using Picasso
        Picasso.get().load(currentQuestionOptions[0].bandera).fit().centerCrop()
            .into(binding.ivBandera2)

        // Set the country name options to RadioButtons
        binding.opt1.text = currentQuestionOptions[0].nombre
        binding.opt2.text = currentQuestionOptions[1].nombre
        binding.opt3.text = currentQuestionOptions[2].nombre
        binding.opt4.text = currentQuestionOptions[3].nombre
    }
}