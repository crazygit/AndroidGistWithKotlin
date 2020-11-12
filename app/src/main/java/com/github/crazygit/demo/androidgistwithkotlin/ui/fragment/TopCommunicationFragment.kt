package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.FragmentTopCommunicationBinding


class TopCommunicationFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentTopCommunicationBinding? = null

    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopCommunicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.talkToActivity.setOnClickListener {
            (activity as FragmentCommunicationActivity).changeActivityText("Received msg from top fragment")
        }
        binding.talkToBottomFragment.setOnClickListener {
            ((activity as FragmentCommunicationActivity).supportFragmentManager.findFragmentById(R.id.bottomFragmentInCommunication) as BottomCommunicationFragment).changeBottomFragmentText(
                "Received msg from top fragment"
            )
        }
    }

    fun changeTopFragmentText(text: String) {
        binding.topFragmentMsg.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}