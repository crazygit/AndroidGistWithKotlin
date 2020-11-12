package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityFragmentCommunicationBinding

class FragmentCommunicationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFragmentCommunicationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentCommunicationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.talkToTopFragment.setOnClickListener {
            val topFragmentInCommunication =
                supportFragmentManager.findFragmentById(R.id.topFragmentInCommunication) as TopCommunicationFragment
            topFragmentInCommunication.changeTopFragmentText("received msg from activity")
        }
        binding.talkToBottomFragment.setOnClickListener {
            val bottomFragmentInCommunication =
                supportFragmentManager.findFragmentById(R.id.bottomFragmentInCommunication) as BottomCommunicationFragment
            bottomFragmentInCommunication.changeBottomFragmentText("received msg from activity")
        }
    }

    fun changeActivityText(text: String) {
        binding.activityMsg.text = text
    }
}