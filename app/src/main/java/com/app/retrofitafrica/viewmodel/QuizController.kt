package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.dao.PaisDao
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises

class QuizController(context : Context) {
    private val rightAnswerList = arrayListOf<Pais>()
    private val wrongAnswerList = arrayListOf<Pais>()
    private var listPaises = arrayListOf<Pais>()

    private val listAnsweredQuestion = arrayListOf<Pais>()
    private var currentQuestion = arrayListOf<Pais>()

    private val db : AppDatabasePaises
    private val paisDao : PaisDao

    init {
        db = Room.databaseBuilder(context, AppDatabasePaises::class.java, "Paises").build()
        paisDao = db.paisDao()
        listPaises = paisDao.getAllPaises() as ArrayList<Pais>
    }

    private fun getNewQuestion() : ArrayList<Pais> {
        do {
            currentQuestion= listPaises.shuffled().take(4) as ArrayList<Pais>
        } while (listAnsweredQuestion.contains(currentQuestion[0]))
        listAnsweredQuestion.add(currentQuestion[0])

        return currentQuestion
    }

    fun rightAnswer(pais : Pais) {
        rightAnswerList.add(pais)
    }

    fun wrongAnswer(pais : Pais) {
        wrongAnswerList.add(pais)
    }

}