package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises


class DBPaises {
    fun getPaises(context: Context) : ArrayList<Pais> {
        val db = Room.databaseBuilder(context, AppDatabasePaises::class.java, "Paises").build()
        val paisDao = db.paisDao()
        return paisDao.getAllPaises() as ArrayList<Pais>
    }
}