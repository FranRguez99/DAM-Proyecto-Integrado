package com.app.retrofitafrica.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.app.retrofitafrica.model.Pais
import com.app.retrofitafrica.viewmodel.dao.PaisDao
import com.app.retrofitafrica.viewmodel.database.AppDatabasePaises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DBPaises(context: Context) : ViewModel() {
    private val listPaisesLiveData : MutableLiveData<ArrayList<Pais>?> = MutableLiveData()
    private val countPaisesLiveData: MutableLiveData<Int?> = MutableLiveData()

    private val db : AppDatabasePaises
    private val paisDao : PaisDao

    init {
        db = Room.databaseBuilder(context, AppDatabasePaises::class.java, "Paises").build()
        paisDao = db.paisDao()
    }

    fun cargarListaPaises() {
        viewModelScope.launch(Dispatchers.IO) {
            val listPaises = paisDao.getAllPaises() as ArrayList<Pais>
            withContext(Dispatchers.Main) {
                listPaisesLiveData.value = listPaises
            }

        }
    }

    fun cargarNumPaises () {
        viewModelScope.launch(Dispatchers.IO) {
            val numPaises = paisDao.getCountPaises()
            withContext(Dispatchers.Main) {
                countPaisesLiveData.value = numPaises
            }

        }
    }
    fun getPaisesLiveData() : MutableLiveData<ArrayList<Pais>?> {
       return listPaisesLiveData
    }

    fun getCountPaisesLiveData() : MutableLiveData<Int?> {
        return countPaisesLiveData
    }

    override fun onCleared() {
        super.onCleared()
        db.close()
    }

}