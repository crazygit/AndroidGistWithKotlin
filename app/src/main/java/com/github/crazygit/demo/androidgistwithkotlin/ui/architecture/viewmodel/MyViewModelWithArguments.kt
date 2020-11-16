package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.viewmodel

import androidx.lifecycle.ViewModel


class MyViewModelWithArguments(counterReserved: Int) : ViewModel() {
    var counter = counterReserved
}