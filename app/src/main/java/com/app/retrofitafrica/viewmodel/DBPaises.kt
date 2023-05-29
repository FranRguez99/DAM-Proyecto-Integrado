package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DBPaises : ViewModel() {
    private val listPaisesLiveData : MutableLiveData<ArrayList<Pais>?> = MutableLiveData()

    fun cargarPaises(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = withContext(Dispatchers.IO) {
                Room.databaseBuilder(context, AppDatabasePaises::class.java, "Paises").build()
            }
            val paisDao = db.paisDao()
            val listPaises = paisDao.getAllPaises() as ArrayList<Pais>
            withContext(Dispatchers.Main) {
                listPaisesLiveData.value = listPaises
            }

        }
    }
    fun getPaisesLiveData() : MutableLiveData<ArrayList<Pais>?> {
       return listPaisesLiveData
    }

}