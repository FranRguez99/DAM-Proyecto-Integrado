package com.app.retrofitafrica.viewmodel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.retrofitafrica.model.Score
import com.app.retrofitafrica.viewmodel.dao.ScoreDao


@Database(entities = [Score::class], version = 1)
abstract class AppDatabaseScore : RoomDatabase() {
    abstract fun scoreDao() : ScoreDao
}