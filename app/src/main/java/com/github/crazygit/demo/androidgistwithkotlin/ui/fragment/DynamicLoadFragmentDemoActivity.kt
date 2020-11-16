package com.github.crazygit.demo.androidgistwithkotlin.ui.fragment

import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.crazygit.demo.androidgistwithkotlin.BaseActivity
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityDynamicLoadFragmentDemoBinding

class DynamicLoadFragmentDemoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDynamicLoadFragmentDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val button = findViewById<Button>(R.id.changeBottomFragment)
        button.setOnClickListener {
            replaceFragment(AnotherBottomFragment())
        }
        replaceFragment(BottomFragment())
    }


    /**
     *
     * 动态添加Fragment可以分为5步
     *
     * 1. 创建待添加的Fragment实例
     * 2. 获取FragmentManager对象, 在Activity中直接通过getSupportFragmentManager()方法获取
     * 3. 开启一个事务, 通过beginTransaction()方法实现
     * 4. 向容器添加fragment实例，一般使用replace()方法实现, 需要传入容器id和待添加的fragment实例
     * 5. 提交事务，调用commit()方法来完成
     *
     * 如果需要将事务添加到返回栈中，可以使用addToBackStack()方法来实现
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.bottomLayout, fragment)

//      将事务添加到返回栈中，根据实际使用场景考虑是否需要开启
        transaction.addToBackStack(null)

        transaction.commit()
    }
}