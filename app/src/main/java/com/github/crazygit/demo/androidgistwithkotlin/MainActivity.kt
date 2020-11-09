package com.github.crazygit.demo.androidgistwithkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityMainBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint
import com.github.crazygit.demo.androidgistwithkotlin.ui.FragmentDemo
import com.github.crazygit.demo.androidgistwithkotlin.ui.RecycleViewDemo

class MainActivity : AppCompatActivity() {
    private lateinit var entryPoints: List<EntryPoint>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!::entryPoints.isInitialized) {
            entryPoints = getEntryPoints()
        }
        val mainAdapter = MainAdapter(entryPoints, this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEntryPoints.adapter = mainAdapter
        binding.recyclerViewEntryPoints.layoutManager = layoutManager
        binding.recyclerViewEntryPoints.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewEntryPoints.context,
                layoutManager.orientation,
            )
        )
    }

    private fun getEntryPoints(): List<EntryPoint> {
        return listOf(
            EntryPoint("RecycleView", RecycleViewDemo::class.java),
            EntryPoint("Fragment", FragmentDemo::class.java),
        )
    }
}