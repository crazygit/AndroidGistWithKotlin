package com.github.crazygit.demo.androidgistwithkotlin.navigation

import android.os.Bundle
import androidx.navigation.findNavController
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityNavigationMainBinding

class NavigationMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNavigationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.music -> {
                    binding.navHostFragment.findNavController().navigate(R.id.musicFragment)
                    true
                }
                R.id.video -> {
                    binding.navHostFragment.findNavController().navigate(R.id.videoFragment)
                    true
                }
                R.id.people -> {
                    true
                }
                else -> false
            }
        }
    }
}