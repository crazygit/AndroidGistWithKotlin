package com.github.crazygit.demo.androidgistwithkotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.Menu

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val menu: Menu?) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(menu) as T

}