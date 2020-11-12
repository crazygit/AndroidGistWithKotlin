package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.FragmentBottomCommunicationBinding


class BottomCommunicationFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var _binding: FragmentBottomCommunicationBinding? = null

    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomCommunicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.talkToActivity.setOnClickListener {
            (activity as FragmentCommunicationActivity).changeActivityText("Received msg from bottom fragment")
        }
        binding.talkToTopFragment.setOnClickListener {
            ((activity as FragmentCommunicationActivity).supportFragmentManager.findFragmentById(R.id.topFragmentInCommunication) as TopCommunicationFragment).changeTopFragmentText(
                "Received msg from bottom fragment"
            )
        }
    }


    fun changeBottomFragmentText(text: String) {
        binding.bottomFragmentMsg.text = text
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}