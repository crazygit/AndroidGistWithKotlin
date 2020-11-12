package com.github.crazygit.demo.androidgistwithkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle()
    }

    private fun setActionBarTitle() {
        val title = intent.getStringExtra("title")
        title?.let {
            supportActionBar?.title = it
        }
    }
}