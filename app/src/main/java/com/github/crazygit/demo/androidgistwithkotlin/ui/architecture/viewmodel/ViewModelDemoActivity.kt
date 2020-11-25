package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.edit
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityViewModelDemoBinding

class ViewModelDemoActivity : BaseActivity() {
    private lateinit var binding: ActivityViewModelDemoBinding
    private val myViewModel: MyViewModel by viewModels()
    private val myViewModelWithArguments: MyViewModelWithArguments by viewModels {
        MyViewModelFactory(geCountReserved())
    }
    private lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)

        binding.plusOneBtn.setOnClickListener {
            myViewModel.counter++
            myViewModelWithArguments.counter++
            refreshCounter()
            refreshCounterWithArguments()
        }
        binding.clearBtn.setOnClickListener {
            myViewModel.counter = 0
            myViewModelWithArguments.counter = 0
            refreshCounter()
            refreshCounterWithArguments()
        }

        refreshCounter()
        refreshCounterWithArguments()
    }

    private fun geCountReserved(): Int {
        return sp.getInt("count_reserved", 0)
    }

    private fun refreshCounter() {
        binding.infoText.text = myViewModel.counter.toString()
    }

    private fun refreshCounterWithArguments() {
        binding.infoTextWithArguments.text = myViewModelWithArguments.counter.toString()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", myViewModelWithArguments.counter)
        }
    }
}