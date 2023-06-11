package com.app.retrofitafrica.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitafrica.databinding.ActivityGlossaryBinding
import com.app.retrofitafrica.viewmodel.DBPaises
import com.app.retrofitafrica.viewmodel.recyclerview.glossaryrecycler.GlossaryAdapter

class GlossaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlossaryBinding
    private lateinit var dbPaises : DBPaises
    private lateinit var glossaryAdapter :  GlossaryAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        glossaryAdapter = GlossaryAdapter()
        binding.rcPaises.adapter = glossaryAdapter
        binding.rcPaises.layoutManager = LinearLayoutManager(this)

        dbPaises = DBPaises(this)

        dbPaises.getPaisesLiveData().observe(this) { listaPaises ->
            listaPaises?.let {
                glossaryAdapter.setPaises(it)
                glossaryAdapter.notifyDataSetChanged()
            }
        }
        dbPaises.cargarListaPaises()

        binding.ivBackButtonGlossary.setOnClickListener{
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }


}