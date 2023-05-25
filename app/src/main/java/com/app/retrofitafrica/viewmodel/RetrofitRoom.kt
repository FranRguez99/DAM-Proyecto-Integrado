package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRoom {
    private val urlAPI = "https://africapp-258b7-default-rtdb.europe-west1.firebasedatabase.app"
    fun apiToRoom(context: Context) {

        val db = Room.databaseBuilder(context, AppDatabasePaises::class.java, "Paises").build()
        val paisDao = db.paisDao()
        val paisRetrofit = getRetrofit().create(PaisAPI::class.java).getPaises()

        paisRetrofit.execute().body()?.values?.forEach{ pais ->
            paisDao.insertPais(Pais(pais.nombre, pais.capital, pais.bandera))
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(urlAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}