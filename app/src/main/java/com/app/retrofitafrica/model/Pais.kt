package com.app.retrofitafrica.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["nombre"], tableName = "paises")
data class Pais(
    @ColumnInfo(name = "nombre") val nombre : String,
    @ColumnInfo(name = "capital") val capital : String,
    @ColumnInfo(name = "banderaURL") val bandera : String
)
