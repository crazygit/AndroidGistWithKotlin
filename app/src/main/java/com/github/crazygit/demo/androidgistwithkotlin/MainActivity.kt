package com.github.crazygit.demo.androidgistwithkotlin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityMainBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint
import com.github.crazygit.demo.androidgistwithkotlin.ui.FragmentDemo
import com.github.crazygit.demo.androidgistwithkotlin.ui.RecycleViewDemo

class MainActivity : AppCompatActivity(), EntryPointClickListener {
    private lateinit var binding: ActivityMainBinding

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(getMainEntryPoint())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEntryPoints.adapter = mainAdapter
        binding.recyclerViewEntryPoints.layoutManager = layoutManager
        mainViewModel.currentEntryPoint.observe(this) {
            mainAdapter.setCurrentEntryPoint(it)
        }
        // 在recyclerView的每个Item直接添加分隔符
//        binding.recyclerViewEntryPoints.addItemDecoration(
//            DividerItemDecoration(
//                binding.recyclerViewEntryPoints.context,
//                layoutManager.orientation,
//            )
//        )
    }

    private fun getMainEntryPoint(): EntryPoint {
        return EntryPoint(
            "Home", children = listOf(
                EntryPoint("RecycleView", subActivity = RecycleViewDemo::class.java),
                EntryPoint(
                    "Fragment", children =
                    listOf(
                        EntryPoint("Fragment简单使用", subActivity = FragmentDemo::class.java),
                        EntryPoint("Fragment动态加载")
                    ),
                    description = "Fragment的简单用法，动态加载，生命周期，以及与Activity交互"
                ),
            )
        )
    }

    override fun onEntryPointClick(entryPoint: EntryPoint) {
        entryPoint.children?.let {
            mainViewModel.changeCurrentEntryPoint(entryPoint)
        } ?: run {
            entryPoint.startSubActivity(this)
        }
    }

    override fun onBackPressed() {
        mainViewModel.currentEntryPoint.value?.father?.let {
            mainViewModel.changeCurrentEntryPoint(it)
        } ?: kotlin.run {
            super.onBackPressed()
        }
    }
}