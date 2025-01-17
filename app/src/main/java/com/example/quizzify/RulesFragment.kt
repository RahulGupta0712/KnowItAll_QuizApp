package com.example.quizzify

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizzify.databinding.FragmentRulesBinding
import render.animations.Bounce
import render.animations.Render
import render.animations.Zoom


class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val render = Render(context as MainActivity)
        render.setAnimation(Bounce().InUp(binding.root))
        render.start()

        binding.exitRules.setOnClickListener {
            render.setAnimation(Zoom().Out(binding.root))
            render.start()
            Handler(Looper.getMainLooper()).postDelayed({
                requireActivity().supportFragmentManager.popBackStack()
            }, 1000)
        }
    }


}