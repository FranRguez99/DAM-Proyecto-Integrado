package com.app.retrofitafrica.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.PaisResponse
import com.app.retrofitafrica.databinding.ItemPaisBinding

class PaisAdapter(val listaPaises: ArrayList<PaisResponse>) : RecyclerView.Adapter<PaisViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPaisBinding.inflate(layoutInflater)
        return PaisViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaisViewHolder, position: Int) {
        val item = listaPaises[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return listaPaises.size
    }
}