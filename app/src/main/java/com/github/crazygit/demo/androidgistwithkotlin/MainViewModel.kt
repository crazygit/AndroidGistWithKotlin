package com.github.crazygit.demo.androidgistwithkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint

class MainViewModel(entryPoint: EntryPoint?) : ViewModel() {
    val currentEntryPoint: LiveData<EntryPoint?>
        get() = _currentEntryPoint
    private var _currentEntryPoint = MutableLiveData<EntryPoint?>()

    init {
        changeCurrentEntryPoint(entryPoint)
    }

    fun changeCurrentEntryPoint(entryPoint: EntryPoint?) {
        _currentEntryPoint.value = entryPoint
    }


}