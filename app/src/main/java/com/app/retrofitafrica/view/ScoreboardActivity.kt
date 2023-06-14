package com.app.retrofitafrica.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitafrica.databinding.ActivityScoreboardBinding
import com.app.retrofitafrica.viewmodel.DBScore
import com.app.retrofitafrica.viewmodel.recyclerview.scorerecycler.ScoreAdapter

class ScoreboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreboardBinding
    private lateinit var dbScore: DBScore
    private lateinit var scoreAdapter: ScoreAdapter
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scoreAdapter = ScoreAdapter()
        binding.rcScores.adapter = scoreAdapter
        binding.rcScores.layoutManager = LinearLayoutManager(this)

        dbScore = DBScore(this)

        dbScore.getScoreLiveData().observe(this) { scoreList ->
            scoreList?.let {
                scoreAdapter.setScores(it)
                scoreAdapter.notifyDataSetChanged()
            }
        }
        dbScore.loadScores()
        binding.ivBackButtonScore.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }
}
