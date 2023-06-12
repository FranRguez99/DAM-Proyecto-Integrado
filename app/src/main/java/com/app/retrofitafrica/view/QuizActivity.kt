package com.app.retrofitafrica.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.retrofitafrica.databinding.ActivityQuizBinding
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.QuizController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuizActivity : AppCompatActivity() {
    private var numPreguntas : Int = 0
    private lateinit var binding: ActivityQuizBinding
    private lateinit var quizController: QuizController
    private lateinit var currentQuestionOptions: ArrayList<Pais>

    private lateinit var rightAnswer : Pais
    private lateinit var selectedAnswer : Pais
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numPreguntas = intent.getIntExtra("numPreguntas", 1)

        quizController = QuizController(this, numPreguntas)
        lifecycleScope.launch(Dispatchers.IO) {
            quizController.loadData()
            withContext(Dispatchers.Main) {
                loadQuestion()
            }
        }



        binding.btNextQuestion.setOnClickListener {
            if (binding.radioGroup.checkedRadioButtonId != -1) {
                checkAnswer()
                if (quizController.hasMoreQuestions()) {
                    loadQuestion()
                } else {
                    mostrarToast("TEST COMPLETADO")
                }
            } else {
                mostrarToast("Debe seleccionar una opción.")
            }


        }
    }

    private fun loadQuestion() {
        binding.radioGroup.clearCheck()
        currentQuestionOptions = quizController.getNewQuestion()
        rightAnswer = currentQuestionOptions[0]

        currentQuestionOptions.shuffled()

        Picasso.get().load(rightAnswer.bandera).fit().centerCrop()
            .into(binding.ivBandera2)

        binding.tvPaisQuestion.text = rightAnswer.nombre

        binding.opt1.text = currentQuestionOptions[0].capital
        binding.opt2.text = currentQuestionOptions[1].capital
        binding.opt3.text = currentQuestionOptions[2].capital
        binding.opt4.text = currentQuestionOptions[3].capital
    }

    private fun checkAnswer() {
        val selectedOption = binding.radioGroup.checkedRadioButtonId
        selectedAnswer = when(selectedOption) {
            binding.opt1.id -> currentQuestionOptions[0]
            binding.opt2.id -> currentQuestionOptions[1]
            binding.opt3.id -> currentQuestionOptions[2]
            binding.opt4.id -> currentQuestionOptions[3]
            else -> {Pais("", "", "")}
        }

        if (rightAnswer == selectedAnswer) {
            quizController.rightAnswer(selectedAnswer)
            mostrarToast("Respuesta Correcta")
        } else {
            quizController.wrongAnswer(selectedAnswer)
            mostrarToast("Respuesta Incorrecta")
        }

    }

    private fun mostrarToast(message : String) {
        Toast.makeText(this, message , Toast.LENGTH_SHORT).show()
    }
}