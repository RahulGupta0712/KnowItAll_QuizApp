package com.example.quizzify

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzify.databinding.ActivityScorePageBinding
import render.animations.Flip
import render.animations.Render
import render.animations.Zoom


class ScorePage : AppCompatActivity() {
    private val binding: ActivityScorePageBinding by lazy {
        ActivityScorePageBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val score = intent.getIntExtra("score", 0)
        val size = DataQnA().getData().size

        binding.showScore.text = "$score / $size"

        binding.textView3.text = when (score) {
            in 0..3 -> "Better luck next time! At least you learned some cool facts today!"
            in 4..7 -> "A respectable score! Quizzes are a great way to keep learning new things."
            in 8..9 -> "Wow, amazing job! You aced that quiz!"
            10 -> "Absolutely incredible! You completely dominated the quiz!"
            else -> ""
        }

        binding.backToHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // animation
        val render = Render(this)
        render.setAnimation(Zoom().InDown(binding.textView3))
        render.start()

        render.setAnimation(Zoom().In(binding.textView4))
        render.start()

        render.setAnimation(Flip().InY(binding.showScore))
        render.setDuration(2000)
        render.start()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }
}