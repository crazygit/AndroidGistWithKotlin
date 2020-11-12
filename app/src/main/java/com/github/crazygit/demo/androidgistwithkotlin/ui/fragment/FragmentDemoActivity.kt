package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityFragmentDemoBinding


class FragmentDemoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFragmentDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.findFragmentById(R.id.topFragment)
    }
}