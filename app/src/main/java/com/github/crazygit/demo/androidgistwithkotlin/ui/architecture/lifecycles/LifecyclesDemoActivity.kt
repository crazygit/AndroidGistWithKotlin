package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.lifecycles

import android.os.Bundle
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.R

class LifecyclesDemoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles_demo)
        // 如果需要主动获取当前Activity的生命周期状态
        // 可以把lifecycle对象传递给MyObserver
        // 然后通过lifecycle.currentState来主动获取当前Activity的生命周期状态
        lifecycle.addObserver(MyObserver(lifecycle))
    }
}