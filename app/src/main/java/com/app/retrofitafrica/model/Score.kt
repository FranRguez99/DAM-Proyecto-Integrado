package com.app.retrofitafrica.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scoreboard")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    @ColumnInfo(name = "numPreguntas")
    val numPreguntas : Int,

    @ColumnInfo(name = "rightAnswers")
    val rightAnswers : Int,

    @ColumnInfo(name = "wrongAnswers")
    val wrongAnswers : Int ,

    @ColumnInfo(name = "fechaRealizacion")
    val fechaRealizacion : String
)
