package com.example.quizzify

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizzify.databinding.FragmentQuestionsBinding
import com.shashank.sony.fancytoastlib.FancyToast

import render.animations.Fade
import render.animations.Render

import render.animations.Zoom


class QuestionsFragment(private var questionId: Int, private var score: Int) : Fragment() {
    private lateinit var binding: FragmentQuestionsBinding
    private var click = 0
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuestionsBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set animation
        val render = Render(context as MainActivity)
        render.setAnimation(Fade().In(binding.question))
        render.setDuration(500)
        render.start()

        render.setAnimation(Zoom().InLeft(binding.option1))
        render.setDuration(700)
        render.start()

        render.setAnimation(Zoom().InRight(binding.option2))
        render.setDuration(700)
        render.start()

        render.setAnimation(Zoom().InLeft(binding.option3))
        render.setDuration(700)
        render.start()

        render.setAnimation(Zoom().InRight(binding.option4))
        render.setDuration(700)
        render.start()

        render.setAnimation(Fade().In(binding.cardView))
        render.start()

        val wholeData = DataQnA().getData()
        val size = wholeData.size
        val data = wholeData[questionId]

        binding.question.text = "${questionId + 1}. ${data.question}"
        binding.option1.text = "A : ${data.optionA}"
        binding.option2.text = "B : ${data.optionB}"
        binding.option3.text = "C : ${data.optionC}"
        binding.option4.text = "D : ${data.optionD}"


        // making all options height equal
        val heights = ArrayList<Int>()
        var maxHeight: Int
        val viewTreeObserver = binding.root.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                heights.add(binding.option1.height)
                heights.add(binding.option2.height)
                heights.add(binding.option3.height)
                heights.add(binding.option4.height)

                maxHeight = heights.max()
                binding.option1.height = maxHeight
                binding.option2.height = maxHeight
                binding.option3.height = maxHeight
                binding.option4.height = maxHeight

                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        val correctAns = data.correctOption

        // setting timer
        val totalTime: Long = 31 // in seconds
        val updateInterval: Long = 1000 // in ms
        timer = Timer(totalTime * 1000, updateInterval, binding)

        timer.start()

        if (questionId == size - 1) {
            binding.nextButton.text = getString(R.string.submit_quiz)
        }

        val selectedColor = ContextCompat.getColorStateList(context as AppCompatActivity, R.color.tint_button)
        val unselectedColor = ContextCompat.getColorStateList(context as AppCompatActivity, R.color.unselected_color)
        binding.option1.setOnClickListener {
            binding.option1.setBackgroundTintList(selectedColor)
            binding.option2.setBackgroundTintList(unselectedColor)
            binding.option3.setBackgroundTintList(unselectedColor)
            binding.option4.setBackgroundTintList(unselectedColor)
            click = 1
        }

        binding.option2.setOnClickListener {
            binding.option1.setBackgroundTintList(unselectedColor)
            binding.option2.setBackgroundTintList(selectedColor)
            binding.option3.setBackgroundTintList(unselectedColor)
            binding.option4.setBackgroundTintList(unselectedColor)
            click = 2
        }

        binding.option3.setOnClickListener {
            binding.option1.setBackgroundTintList(unselectedColor)
            binding.option2.setBackgroundTintList(unselectedColor)
            binding.option3.setBackgroundTintList(selectedColor)
            binding.option4.setBackgroundTintList(unselectedColor)
            click = 3
        }

        binding.option4.setOnClickListener {
            binding.option1.setBackgroundTintList(unselectedColor)
            binding.option2.setBackgroundTintList(unselectedColor)
            binding.option3.setBackgroundTintList(unselectedColor)
            binding.option4.setBackgroundTintList(selectedColor)
            click = 4
        }

        val activity = context as AppCompatActivity

        val correct = ContextCompat.getColorStateList(context as AppCompatActivity, R.color.correct_ans)
        val incorrect = ContextCompat.getColorStateList(context as AppCompatActivity, R.color.incorrect_ans)

        binding.nextButton.setOnClickListener {

            when (click) {
                0 -> FancyToast.makeText(
                    activity,
                    "Choose any option first",
                    FancyToast.LENGTH_LONG,
                    FancyToast.DEFAULT,
                    false
                ).show()

                1 -> if (correctAns == data.optionA) {
                    binding.option1.setBackgroundTintList(correct)  // if correct then change to green color
                    score++
                } else {
                    binding.option1.setBackgroundTintList(incorrect)    // else red color
                }

                2 -> if (correctAns == data.optionB) {
                    binding.option2.setBackgroundTintList(correct)
                    score++
                } else {
                    binding.option2.setBackgroundTintList(incorrect)
                }

                3 -> if (correctAns == data.optionC) {
                    binding.option3.setBackgroundTintList(correct)
                    score++
                } else {
                    binding.option3.setBackgroundTintList(incorrect)
                }

                4 -> if (correctAns == data.optionD) {
                    binding.option4.setBackgroundTintList(correct)
                    score++
                } else {
                    binding.option4.setBackgroundTintList(incorrect)
                }
            }

            if (click != 0) {
                timer.cancel()

                // displaying correct answer
                if (correctAns == data.optionA) binding.option1.setBackgroundTintList(correct)
                if (correctAns == data.optionB) binding.option2.setBackgroundTintList(correct)
                if (correctAns == data.optionC) binding.option3.setBackgroundTintList(correct)
                if (correctAns == data.optionD) binding.option4.setBackgroundTintList(correct)

                Handler(Looper.getMainLooper()).postDelayed({
                    if (questionId == size - 1) {
                        val intent = Intent(activity, ScorePage::class.java)
                        intent.putExtra("score", score)
                        startActivity(intent)
                    } else {
                        val trans = activity.supportFragmentManager.beginTransaction()
                        trans.replace(R.id.frame, QuestionsFragment(questionId + 1, score))
                        trans.addToBackStack(null)
                        trans.commit()
                    }
                }, 100)


            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()  // very very important to add this, as in main activity we have set custom back click listener, so there it may clear whole backstack, but there might be a chance that the timer may be running, so it gives an IllegalState Error, so that's why when this fragment is removed from back stack , we also close the timer associated.
    }
}