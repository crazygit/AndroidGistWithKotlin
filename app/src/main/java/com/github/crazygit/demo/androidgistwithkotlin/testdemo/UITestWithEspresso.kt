package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import android.os.Bundle
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityUiTestWithEspressoBinding

class UITestWithEspresso : BaseActivity() {

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUiTestWithEspressoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.incrementButton.setOnClickListener {
            binding.result.text = count.toString()
            count++
        }
    }
}