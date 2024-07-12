package com.example.quizzify

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

import androidx.appcompat.app.AppCompatActivity
import com.example.quizzify.databinding.FragmentQuestionsBinding
import com.shashank.sony.fancytoastlib.FancyToast

import render.animations.Fade
import render.animations.Render

import render.animations.Zoom


@Suppress("DEPRECATION")
class QuestionsFragment(var questionId: Int, var score: Int) : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private var click = 0
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

        val wholeData = DataQnA().getData()
        val size = wholeData.size
        val data = wholeData[questionId]

        binding.question.text = "${questionId + 1}. ${data.question}"
        binding.option1.text = "A : ${data.optionA}"
        binding.option2.text = "B : ${data.optionB}"
        binding.option3.text = "C : ${data.optionC}"
        binding.option4.text = "D : ${data.optionD}"

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

        if (questionId == size - 1) {
            binding.nextButton.text = getString(R.string.submit_quiz)
        }

        binding.option1.setOnClickListener {
            binding.option1.setTextColor(resources.getColor(R.color.yellow))
            binding.option2.setTextColor(resources.getColor(R.color.white))
            binding.option3.setTextColor(resources.getColor(R.color.white))
            binding.option4.setTextColor(resources.getColor(R.color.white))
            click = 1
        }

        binding.option2.setOnClickListener {
            binding.option1.setTextColor(resources.getColor(R.color.white))
            binding.option2.setTextColor(resources.getColor(R.color.yellow))
            binding.option3.setTextColor(resources.getColor(R.color.white))
            binding.option4.setTextColor(resources.getColor(R.color.white))
            click = 2
        }

        binding.option3.setOnClickListener {
            binding.option1.setTextColor(resources.getColor(R.color.white))
            binding.option2.setTextColor(resources.getColor(R.color.white))
            binding.option3.setTextColor(resources.getColor(R.color.yellow))
            binding.option4.setTextColor(resources.getColor(R.color.white))
            click = 3
        }

        binding.option4.setOnClickListener {
            binding.option1.setTextColor(resources.getColor(R.color.white))
            binding.option2.setTextColor(resources.getColor(R.color.white))
            binding.option3.setTextColor(resources.getColor(R.color.white))
            binding.option4.setTextColor(resources.getColor(R.color.yellow))
            click = 4
        }

        binding.nextButton.setOnClickListener {
            when (click) {
                0 -> FancyToast.makeText(context as AppCompatActivity, "Choose any option first", FancyToast.LENGTH_LONG, FancyToast.DEFAULT,false).show()
                1 -> if (correctAns == data.optionA) score++
                2 -> if (correctAns == data.optionB) score++
                3 -> if (correctAns == data.optionC) score++
                4 -> if (correctAns == data.optionD) score++
            }

            if (click != 0) {
                if (questionId == size - 1) {
                    val intent = Intent(requireActivity(), ScorePage::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                } else {
                    val trans = requireActivity().supportFragmentManager.beginTransaction()
                    trans.replace(R.id.frame, QuestionsFragment(questionId + 1, score))
                    trans.addToBackStack(null)
                    trans.commit()
                }
            }
        }
    }

    companion object {

    }


}