package com.app.retrofitafrica.recyclerView

import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.PaisResponse
import com.app.retrofitafrica.databinding.ItemPaisBinding
import com.squareup.picasso.Picasso

class PaisViewHolder(val binding: ItemPaisBinding) : RecyclerView.ViewHolder(binding.root)  {
    fun render(pais : PaisResponse) {
        Picasso.get().load(pais.bandera).into(binding.imageView)
        binding.tvCapital.text = pais.capital
        binding.tvPais.text = pais.nombre
    }
}