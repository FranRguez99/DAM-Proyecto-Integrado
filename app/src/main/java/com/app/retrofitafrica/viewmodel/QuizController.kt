package com.app.retrofitafrica.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.model.Score
import com.app.retrofitafrica.view.ScoreActivity
import com.app.retrofitafrica.viewmodel.dao.PaisDao
import com.app.retrofitafrica.viewmodel.dao.ScoreDao
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises
import com.app.retrofitafrica.viewmodel.database.AppDatabaseScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

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

    private lateinit var dbPaises: AppDatabasePaises
    private lateinit var paisDao: PaisDao

    private lateinit var dbScore: AppDatabaseScore
    private lateinit var scoreDao: ScoreDao

    init {
        this.context = contextActivity
        this.numPreguntas = numPreg
    }
    suspend fun loadData(){
            dbPaises = Room.databaseBuilder(this.context, AppDatabasePaises::class.java, "Paises").build()
            paisDao = dbPaises.paisDao()

            dbScore = Room.databaseBuilder(this.context, AppDatabaseScore::class.java, "Scoreboard").build()
            scoreDao = dbScore.scoreDao()

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitScore() {
        viewModelScope.launch(Dispatchers.IO) {
            scoreDao.insertScore(Score(0, numPreguntas + 1, rightAnswerList.size, wrongAnswerList.size, LocalDate.now().toString()))
            withContext(Dispatchers.Main) {
                context.startActivity(Intent(context, ScoreActivity::class.java)
                    .putExtra("numPreguntas", numPreguntas )
                    .putExtra("numAciertos",rightAnswerList.size )
                    .putExtra("numFallos", wrongAnswerList.size )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        dbPaises.close()
        dbScore.close()
    }
}
