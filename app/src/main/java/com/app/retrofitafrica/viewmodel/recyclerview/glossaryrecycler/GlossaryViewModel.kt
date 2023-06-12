package com.app.retrofitafrica.viewmodel.recyclerview.glossaryrecycler

import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemPaisBinding
import com.app.retrofitafrica.model.Pais
import com.squareup.picasso.Picasso

class GlossaryViewModel(private val binding: ItemPaisBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(item: Pais, selected: Boolean) {
        Picasso.get().load(item.bandera).fit().centerCrop().into(binding.ivBandera)
        binding.tvPais.text = item.nombre
        binding.tvCapital.text = item.capital

        binding.root.isSelected = selected
    }
}
