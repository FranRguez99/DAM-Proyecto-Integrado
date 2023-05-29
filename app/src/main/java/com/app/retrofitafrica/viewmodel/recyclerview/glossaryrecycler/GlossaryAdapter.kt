package com.app.retrofitafrica.viewmodel.recyclerview.glossaryrecycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemPaisBinding
import com.app.retrofitafrica.model.Pais

class GlossaryAdapter : RecyclerView.Adapter<GlossaryViewModel>() {
    private val listaPaises = ArrayList<Pais>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlossaryViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GlossaryViewModel(ItemPaisBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int = listaPaises.size

    override fun onBindViewHolder(holder: GlossaryViewModel, position: Int) {
        val item = listaPaises[position]
        holder.render(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPaises(listaPaises: ArrayList<Pais>) {
        this.listaPaises.clear()
        this.listaPaises.addAll(listaPaises)
        notifyDataSetChanged()
    }
}