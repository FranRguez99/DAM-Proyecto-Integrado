package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitafrica.databinding.ActivityGlossaryBinding
import com.app.retrofitafrica.viewmodel.DBPaises
import com.app.retrofitafrica.viewmodel.recyclerview.glossaryrecycler.GlossaryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GlossaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlossaryBinding
    private var dbPaises = DBPaises()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.ivBackButtonGlossary.setOnClickListener{
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        val context = this
        CoroutineScope(Dispatchers.IO).launch {
            val listaPaises = dbPaises.getPaises(context)

            withContext(Dispatchers.Main) {
                binding.rcPaises.layoutManager = LinearLayoutManager(context)
                binding.rcPaises.adapter = GlossaryAdapter(listaPaises)
            }


    }

    }
}