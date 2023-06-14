package com.app.retrofitafrica.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitafrica.databinding.ActivityScoreBinding
import com.app.retrofitafrica.viewmodel.DBScore
import com.app.retrofitafrica.viewmodel.recyclerview.scorerecycler.ScoreAdapter

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    private lateinit var dbScore: DBScore
    private lateinit var scoreAdapter: ScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
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
