package com.app.retrofitafrica.viewmodel.recyclerview.glossaryrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemPaisBinding
import com.app.retrofitafrica.model.Pais

class GlossaryAdapter(private val listaPaises : ArrayList<Pais>) : RecyclerView.Adapter<GlossaryViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlossaryViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GlossaryViewModel(ItemPaisBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int = listaPaises.size

    override fun onBindViewHolder(holder: GlossaryViewModel, position: Int) {
        val item = listaPaises[position]
        holder.render(item)
    }
}