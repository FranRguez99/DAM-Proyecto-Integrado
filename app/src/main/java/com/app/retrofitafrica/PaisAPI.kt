package com.app.retrofitafrica

import retrofit2.Call
import retrofit2.http.GET

interface PaisAPI {
    @GET("paises.json")
    fun getPaises(): Call<Map<String, PaisResponse>>

}