package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.crazygit.demo.androidgistwithkotlin.databinding.TopFragmentBinding

class TopFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: TopFragmentBinding? = null

    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TopFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}