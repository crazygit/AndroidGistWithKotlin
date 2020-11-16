package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.livedata

import android.os.Bundle
import androidx.activity.viewModels
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityLiveDataDemoBinding

class LiveDataDemoActivity : BaseActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private val liveDataViewModel: LiveDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLiveDataDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.plusOneBtn.setOnClickListener {
            liveDataViewModel.plusOne()
        }
        binding.clearBtn.setOnClickListener {
            liveDataViewModel.clear()
        }
        liveDataViewModel.counter.observe(this) {
            binding.infoText.text = it.toString()
        }
        userViewModel.initUser(
            User("Jim", "Green", 10)
        )
        binding.changeUserNameBtn.setOnClickListener {
            userViewModel.changeUserName(
                getRandomString(5),
                getRandomString(10)
            )
        }
        userViewModel.userName.observe(this) {
            binding.userNameText.text = it
        }
        binding.changeUserIdBtn.setOnClickListener {
            userViewModel.getUser(getRandomString(3))
        }

        userViewModel.user.observe(this) {
            binding.userIdText.text = it.firstName
        }

    }

    private fun getRandomString(length: Int): String {
        val letters = ('A'..'Z') + ('a'..'z') + (0..9)

        return (1..length).map {
            letters.random()
        }.joinToString("")
    }
}
