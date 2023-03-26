package com.app.retrofitafrica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitafrica.databinding.ActivityMainBinding
import com.app.retrofitafrica.recyclerView.PaisAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listaPaises = arrayListOf<PaisResponse>()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val call = getRetrofit().create(PaisAPI::class.java)

        call.getPaises().enqueue(object : Callback<Map<String, PaisResponse>> {
            override fun onResponse(call: Call<Map<String, PaisResponse>>, response: Response<Map<String, PaisResponse>>) {
                if (response.isSuccessful) {
                    val countries = response.body()

                    // Agrega cada objeto Country a la lista
                    countries?.forEach { (_, value) ->
                        listaPaises.add(value)
                    }

                    initRecyclerView(listaPaises)
                } else {
                    // Aqui va el login
                }
            }
            override fun onFailure(call: Call<Map<String, PaisResponse>>, t: Throwable) {

            }
        })



    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://africapp-258b7-default-rtdb.europe-west1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecyclerView(listaPaises: ArrayList<PaisResponse>) {
        binding.rvPaises.layoutManager = LinearLayoutManager(this)
        binding.rvPaises.adapter = PaisAdapter(listaPaises)
    }
}