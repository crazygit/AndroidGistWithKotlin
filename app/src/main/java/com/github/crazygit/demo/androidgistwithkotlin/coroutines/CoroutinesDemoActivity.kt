package com.github.crazygit.demo.androidgistwithkotlin.coroutines

import android.os.Bundle
import android.widget.Toast
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityCoroutinesDemoBinding
import kotlinx.coroutines.*


// 参考
// https://juejin.im/post/6844903921337516040
class CoroutinesDemoActivity : BaseActivity() {
    private lateinit var binding: ActivityCoroutinesDemoBinding
    private val scope = MainScope() // 不要忘记在onDestroy取消所有的协程

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutinesDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchFromGlobalScope.setOnClickListener {
            launchFromGlobalScope()
        }
        binding.launchFromMainScope.setOnClickListener {
            launchFromMainScope()
        }
    }

    private fun launchFromGlobalScope() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferred = async(Dispatchers.IO) {
                // 模拟请求耗时
                delay(3000)
                "Get text from launchFromGlobalScope"
            }
            binding.infoText.text = deferred.await()
            // 执行这个方法之后，立即按返回键返回上一页面，仍然会弹出 Toast 。
            // 如果是实际开发中通过网络请求更新页面的话，当用户已经不在这个页面了，就根本没有必要再去请求了
            Toast.makeText(applicationContext, "GlobalScope", Toast.LENGTH_SHORT).show()
        }
    }

    private fun launchFromMainScope() {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                // network request
                delay(3000)
                "Get it from MainScope"
            }

            binding.infoText.text = deferred.await()
            Toast.makeText(applicationContext, "MainScope", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}