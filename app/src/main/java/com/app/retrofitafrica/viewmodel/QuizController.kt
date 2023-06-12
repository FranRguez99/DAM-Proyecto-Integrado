package com.app.retrofitafrica.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.dao.PaisDao
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizController(contextActivity: Context, numPreg : Int) : ViewModel(){
    @SuppressLint("StaticFieldLeak")
    private val context: Context
    private val numPreguntas : Int
    private val rightAnswerList = ArrayList<Pais>()
    private val wrongAnswerList = ArrayList<Pais>()
    private var listPaises = arrayListOf<Pais>()

    private val listAnsweredQuestion = ArrayList<Pais>()
    private var currentQuestion = ArrayList<Pais>()
    private var currentQuestionIndex = -1

    private lateinit var db: AppDatabasePaises
    private lateinit var paisDao: PaisDao

    init {
        this.context = contextActivity
        this.numPreguntas = numPreg
    }
    suspend fun loadData(){
            db = Room.databaseBuilder(this.context, AppDatabasePaises::class.java, "Paises").build()
            paisDao = db.paisDao()
            listPaises = withContext(Dispatchers.IO) {
                paisDao.getAllPaises() as ArrayList<Pais>
            }
    }

    fun getNewQuestion(): ArrayList<Pais> {
        if (listPaises.isNotEmpty()) {
            do {
                currentQuestion = listPaises.shuffled().take(4) as ArrayList<Pais>
            } while (listAnsweredQuestion.contains(currentQuestion[0]))
            listAnsweredQuestion.add(currentQuestion[0])
        }
        return currentQuestion
        }

    fun hasMoreQuestions(): Boolean {
        return listAnsweredQuestion.size <= numPreguntas
    }

    fun rightAnswer(pais : Pais) {
        rightAnswerList.add(pais)
    }

    fun wrongAnswer(pais : Pais) {
        wrongAnswerList.add(pais)
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }
}
