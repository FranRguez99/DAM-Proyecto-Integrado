package com.app.retrofitafrica.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class Score(numPreguntas : Int, rightAnswers : Int, wrongAnswers : Int) {
    private val fechaRealizacion : LocalDate = LocalDate.now()
    private var numPreguntas : Int
    private var rightAnswers : Int
    private var wrongAnswers : Int
    init {
        this.numPreguntas = numPreguntas
        this.rightAnswers = rightAnswers
        this.wrongAnswers = wrongAnswers
    }

}