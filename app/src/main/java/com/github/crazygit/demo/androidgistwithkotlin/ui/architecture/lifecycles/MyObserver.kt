package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.lifecycles

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {
    val TAG = "MyObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityCreate() {
        Log.d(TAG, "activity create")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d(TAG, "activity start")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityResume() {
        Log.d(TAG, "activity resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityPause() {
        Log.d(TAG, "activity pause")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d(TAG, "activity stop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun activityDestroy() {
        Log.d(TAG, "activity destroy")
    }

    // Lifecycle.Event.ON_ANY
    // 表示可以匹配Activity的任何生命周期
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun activityAny() {
        Log.d(
            TAG,
            "activity any called, activity current state is: ${lifecycle.currentState}"
        )
    }
}