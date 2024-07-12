package com.example.quizzify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quizzify.databinding.FragmentRulesBinding
import render.animations.Render


class RulesFragment : Fragment() {

    private lateinit var binding:FragmentRulesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRulesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val render = Render(context as MainActivity)

        binding.exitRules.setOnClickListener{
            val manager = requireActivity().supportFragmentManager
            manager.popBackStack()
        }
    }

    companion object {

    }
}