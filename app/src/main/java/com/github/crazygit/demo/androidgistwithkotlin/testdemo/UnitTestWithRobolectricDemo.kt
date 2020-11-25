package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import android.content.Context
import com.github.crazygit.demo.androidgistwithkotlin.R

object UnitTestWithRobolectricDemo {

    fun getStringWithContext(context: Context) = context.getString(R.string.unit_test_get_string)
}