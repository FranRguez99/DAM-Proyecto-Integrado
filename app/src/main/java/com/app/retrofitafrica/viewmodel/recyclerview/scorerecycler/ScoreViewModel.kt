package com.app.retrofitafrica.viewmodel.recyclerview.scorerecycler

import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemScoreBinding
import com.app.retrofitafrica.model.Score

class ScoreViewModel(private val binding: ItemScoreBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(item: Score) {
        binding.tvNumPreguntas.text = item.numPreguntas.toString()
        binding.tvRightAnswers.text = item.rightAnswers.toString()
        binding.tvWrongAnswers.text = item.wrongAnswers.toString()
        binding.tvFechaRealizacion.text = item.fechaRealizacion
    }
}
