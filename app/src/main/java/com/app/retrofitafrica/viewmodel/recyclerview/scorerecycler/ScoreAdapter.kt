package com.app.retrofitafrica.viewmodel.recyclerview.scorerecycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitafrica.databinding.ItemScoreBinding
import com.app.retrofitafrica.model.Score

class ScoreAdapter : RecyclerView.Adapter<ScoreViewModel>() {
    private val scoreList = ArrayList<Score>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScoreViewModel(ItemScoreBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int = scoreList.size

    override fun onBindViewHolder(holder: ScoreViewModel, position: Int) {
        val item = scoreList[position]
        holder.render(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setScores(scoreList: List<Score>) {
        this.scoreList.clear()
        this.scoreList.addAll(scoreList)
        notifyDataSetChanged()
    }
}
