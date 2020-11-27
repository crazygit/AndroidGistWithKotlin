package com.github.crazygit.demo.androidgistwithkotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.Menu

class MainViewModel(menu: Menu?) : ViewModel() {
    val currentMenu: LiveData<Menu?>
        get() = _currentMenu
    private var _currentMenu = MutableLiveData<Menu?>()

    init {
        changeCurrentMenu(menu)
    }

    fun changeCurrentMenu(menu: Menu?) {
        _currentMenu.value = menu
    }


}