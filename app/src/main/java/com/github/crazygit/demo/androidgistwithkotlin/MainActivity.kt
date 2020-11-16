package com.github.crazygit.demo.androidgistwithkotlin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.crazygit.demo.androidgistwithkotlin.coroutines.CoroutinesDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.databinding.ActivityMainBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint
import com.github.crazygit.demo.androidgistwithkotlin.network.NetworkDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.lifecycles.LifecyclesDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.livedata.LiveDataDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.architecture.viewmodel.ViewModelDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.fragment.DynamicLoadFragmentDemoActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.fragment.FragmentCommunicationActivity
import com.github.crazygit.demo.androidgistwithkotlin.ui.fragment.FragmentDemoActivity

class MainActivity : BaseActivity(), EntryPointClickListener {
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
            it?.let {
                supportActionBar?.title = it.title
            }
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
                EntryPoint(
                    "Architecture Components",
                    description = """
                        Android架构组件
                        * App Startup
                        * DataStore
                        * ViewBinding
                        * DataBinding
                        * Live Data
                        * ViewModel
                        * WorkManager
                        * Saving States
                        * Paging Library
                        ......

                        更多内容可以参考:
                        https://developer.android.com/topic/libraries/architecture

                        使用示例:
                        https://github.com/android/architecture-components-samples
                    """.trimIndent(),
                    children = listOf(
                        EntryPoint(
                            "ViewModel", subActivity = ViewModelDemoActivity::class.java,
                            description = """
                            如果要向ViewModel传递参数，需要创建一个Factory类并继承ViewModelProvider.NewInstanceFactory来实现
                        """.trimIndent()
                        ),
                        EntryPoint(
                            "Lifecycles", subActivity = LifecyclesDemoActivity::class.java,
                            description = """
                            1. 创建一个Observer类实现LifecycleObserver接口
                            2. 在Observer类里面定义需要感知生命周期的方法，并给这些方法添加上表示生命周期的@OnLifecycleEvent的注解
                            3. 通过LifecycleOwner对象的addObserver()方法，添加定义好的Observer类
                        """.trimIndent()
                        ),
                        EntryPoint(
                            "LiveData", subActivity = LiveDataDemoActivity::class.java,
                            description = """
                            LiveData的基本函数，以及map和switchMap方法的使用场景
                        """.trimIndent()
                        )

                    )
                ),
                EntryPoint(
                    "UI", description = "User Interface",
                    children = listOf(
                        EntryPoint(
                            "Fragment", children =
                            listOf(
                                EntryPoint(
                                    "Fragment简单使用",
                                    subActivity = FragmentDemoActivity::class.java
                                ),
                                EntryPoint(
                                    "Fragment动态加载",
                                    subActivity = DynamicLoadFragmentDemoActivity::class.java,
                                    description = """
                            动态添加Fragment可以分为5步

                            1. 创建待添加的Fragment实例
                            2. 获取FragmentManager对象, 在Activity中直接通过getSupportFragmentManager()方法获取
                            3. 开启一个事务, 通过beginTransaction()方法实现
                            4. 向容器添加fragment实例，一般使用replace()方法实现, 需要传入容器id和待添加的fragment实例
                            5. 提交事务，调用commit()方法来完成

                            如果需要将事务添加到返回栈中，可以使用addToBackStack()方法来实现
                            """.trimIndent()
                                ),
                                EntryPoint(
                                    "Fragment与Activity交互",
                                    subActivity = FragmentCommunicationActivity::class.java,
                                    description = """
                                Activity -> Fragment

                                1. 获取FragmentManager， 在Activity中直接通过getSupportFragmentManager()方法获取
                                2. 调用FragmentManager的findFragmentById()方法，在Activity中就可以获取Fragment实例
                                3. 调用Fragment里的方法

                                Fragment -> Activity

                                1. 在Fragment里通过getActivity()方法获取Activity实例
                                2. 调用Activity方法

                                Fragment A -> Activity -> Fragment B

                                通过Activity作为桥梁，实现不同Fragment直接的通信
                                1. 在 Fragment A 里通过getActivity()方法获取Activity实例
                                2. 获取FragmentManager， 在Activity中直接通过getSupportFragmentManager()方法获取
                                3. 调用FragmentManager的findFragmentById()方法，在Activity中就可以获取 Fragment B 实例
                                4. 调用 Fragment B 里的方法
                            """.trimIndent()

                                ),
                                EntryPoint(
                                    "Fragment的生命周期", description = """
                            生命周期参考官方文档
                            https://developer.android.com/guide/components/fragments
                        """.trimIndent()
                                ),

                                ),
                            description = "Fragment的简单用法，动态加载，生命周期，以及与Activity交互"
                        ),
                    )
                ),
                EntryPoint(
                    "Network", subActivity = NetworkDemoActivity::class.java,
                    description = """
                    HTTPURLConnection, OkHttp, Retrofit
                    Parse Json Response with JSONObject, GSON
                """.trimIndent()
                ),
                EntryPoint(
                    "Coroutines", subActivity = CoroutinesDemoActivity::class.java,
                    description = """
                        协程相关使用
                    """.trimIndent()
                )
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