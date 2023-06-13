package com.app.retrofitafrica.viewmodel.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.retrofitafrica.model.Score

@Dao
interface ScoreDao {
    @Query("Select * from scoreboard order by (rightAnswers * 100.0) / numPreguntas DESC, numPreguntas DESC")
    fun getAllScoreByResults() : List<Score>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertScore(score : Score)

}