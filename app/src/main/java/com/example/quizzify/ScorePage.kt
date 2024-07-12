package com.example.quizzify

import android.annotation.SuppressLint
import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzify.databinding.ActivityScorePageBinding
import com.shashank.sony.fancytoastlib.FancyToast
import render.animations.Flip
import render.animations.Render
import render.animations.Zoom


class ScorePage : AppCompatActivity() {
    private val binding:ActivityScorePageBinding by lazy{
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

        if(score < 4){
            binding.textView3.text = "OOPS !!!"
        }


        binding.backToHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // animation
        val render = Render(this)
        render.setAnimation(Zoom().In(binding.textView3))
        render.setDuration(500)
        render.start()

        render.setAnimation(Zoom().In(binding.textView4))
        render.setDuration(500)
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