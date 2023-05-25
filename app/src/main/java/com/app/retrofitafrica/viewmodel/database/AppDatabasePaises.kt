package com.app.retrofitafrica.viewmodel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.dao.PaisDao

@Database(entities = [Pais::class], version = 1)
abstract class AppDatabasePaises : RoomDatabase() {
    abstract fun paisDao() : PaisDao
}