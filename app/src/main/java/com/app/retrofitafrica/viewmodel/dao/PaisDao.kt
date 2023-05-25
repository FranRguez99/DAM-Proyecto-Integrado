package com.app.retrofitafrica.viewmodel.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.retrofitafrica.model.Pais

@Dao
interface PaisDao {
    @Query("Select * from paises")
    fun getAll(): List<Pais>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPais(pais : Pais)

}