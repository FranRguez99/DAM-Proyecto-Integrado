package com.app.retrofitafrica.viewmodel.recyclerview.scorerecycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemScoreBinding
import com.app.retrofitafrica.model.Score
import kotlin.math.roundToInt

class ScoreViewModel(private val binding: ItemScoreBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun render(item: Score) {
        binding.tvPercent.text = ((item.rightAnswers * 100.0 / item.numPreguntas).roundToInt().toString()) + "%"
        binding.tvNumPreguntas.text = item.numPreguntas.toString()
        binding.tvRightAnswers.text = item.rightAnswers.toString()
        binding.tvWrongAnswers.text = item.wrongAnswers.toString()
        binding.tvFechaRealizacion.text = item.fechaRealizacion
    }
}