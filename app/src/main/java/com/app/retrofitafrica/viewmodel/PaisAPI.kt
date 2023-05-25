package com.app.retrofitafrica.viewmodel

import com.app.retrofitafrica.model.modelAPI.PaisResponse
import retrofit2.Call
import retrofit2.http.GET

interface PaisAPI {
    @GET("paises.json")
    fun getPaises(): Call<Map<String, PaisResponse>>

}