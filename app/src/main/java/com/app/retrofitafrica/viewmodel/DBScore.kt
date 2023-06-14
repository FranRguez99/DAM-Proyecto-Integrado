package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.app.retrofitafrica.model.Score
import com.app.retrofitafrica.viewmodel.dao.ScoreDao
import com.app.retrofitafrica.viewmodel.database.AppDatabaseScore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DBScore(context : Context) : ViewModel() {
    private val scoreLiveData: MutableLiveData<List<Score>?> = MutableLiveData()

    private val db: AppDatabaseScore
    private val scoreDao: ScoreDao

    init {
        db = Room.databaseBuilder(context, AppDatabaseScore::class.java, "Scoreboard").build()
        scoreDao = db.scoreDao()
    }

    fun loadScores() {
        viewModelScope.launch(Dispatchers.IO) {
            val scores = scoreDao.getAllScoreByResults()
            withContext(Dispatchers.Main) {
                scoreLiveData.value = scores
            }
        }
    }

    fun getScoreLiveData(): MutableLiveData<List<Score>?> {
        return scoreLiveData
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }
}