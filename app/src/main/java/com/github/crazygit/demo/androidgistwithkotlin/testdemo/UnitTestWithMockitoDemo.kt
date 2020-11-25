package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import android.content.Context

object UnitTestWithMockitoDemo {

    fun getStringWithContext(context: Context, stringResId: Int) = context.getString(stringResId)
}