package com.example.quizzify

import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzify.databinding.FragmentQuestionsBinding
import render.animations.Attention
import render.animations.Render

class Timer(totalTime:Long,interval:Long,private var binding : FragmentQuestionsBinding): CountDownTimer(totalTime, interval){

    override fun onTick(millisUntilFinished: Long) {
        val remainingTime = millisUntilFinished/1000 // converting ms to s
        binding.showTime.text = remainingTime.toString()
    }

    override fun onFinish() {
        binding.showTime.setText(R.string.time_up)
        val render = Render(AppCompatActivity())
        render.setAnimation(Attention().Wobble(binding.cardView))
        render.start()
        binding.option2.performClick()  // automatically select B option
        binding.nextButton.performClick()
    }
}