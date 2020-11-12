package com.github.crazygit.demo.androidgistwithkotlin

import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint

interface EntryPointClickListener {
    fun onEntryPointClick(entryPoint: EntryPoint)
}