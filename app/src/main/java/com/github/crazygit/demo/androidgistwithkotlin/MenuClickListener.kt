package com.github.crazygit.demo.androidgistwithkotlin

import com.github.crazygit.demo.androidgistwithkotlin.logic.model.Menu

interface MenuClickListener {
    fun onMenuClick(menu: Menu)
}