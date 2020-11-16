package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {
    fun getUser(userId: String): LiveData<User> {
        return MutableLiveData(
            User(userId, userId, 0)
        )
    }
}