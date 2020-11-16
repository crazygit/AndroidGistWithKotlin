package com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class UserViewModel : ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    private val userIdLiveData = MutableLiveData<String>()

    // map方法的作用是将实际包含数据的LiveData和仅用于观察数据的LiveData进行转换
    // 第一个参数是原始的LiveData对象，
    // 第二个参数是一个转换函数，我们在转换函数里编写逻辑即可
    val userName: LiveData<String> = Transformations.map(
        userLiveData
    ) {
        "${it.firstName} ${it.lastName}"
    }

    fun initUser(user: User) {
        userLiveData.value = user
    }

    fun changeUserName(firstName: String, lastName: String) {
        userLiveData.value = userLiveData.value?.apply {
            this.firstName = firstName
            this.lastName = lastName
        }
    }

    // switchMap方法的作用
    // 如果ViewModel中的某个LiveData对象是调用另外的方法获取的,那么就可以借助switchMap()方法
    // 将这个LiveData对象转换成为另外一个可观察的LiveData对象
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}