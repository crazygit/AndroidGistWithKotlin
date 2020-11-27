package com.github.crazygit.demo.androidgistwithkotlin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.crazygit.demo.androidgistwithkotlin.data.getMainMenu
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityMainBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.Menu

class MainActivity : BaseActivity(), MenuClickListener {
    private lateinit var binding: ActivityMainBinding

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(getMainMenu())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainAdapter = MainAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMenu.adapter = mainAdapter
        binding.recyclerViewMenu.layoutManager = layoutManager
        mainViewModel.currentMenu.observe(this) {
            mainAdapter.setCurrentMenu(it)
            it?.let {
                supportActionBar?.title = it.title
            }
        }
        // 在recyclerView的每个Item直接添加分隔符
//        binding.recyclerViewMenu.addItemDecoration(
//            DividerItemDecoration(
//                binding.recyclerViewMenu.context,
//                layoutManager.orientation,
//            )
//        )
    }

    override fun onMenuClick(menu: Menu) {
        menu.children?.let {
            mainViewModel.changeCurrentMenu(menu)
        } ?: run {
            menu.startSubActivity(this)
        }
    }

    override fun onBackPressed() {
        mainViewModel.currentMenu.value?.father?.let {
            mainViewModel.changeCurrentMenu(it)
        } ?: run {
            super.onBackPressed()
        }
    }
}