package com.github.crazygit.demo.androidgistwithkotlin.logic.model

import android.content.Context
import android.content.Intent

class EntryPoint(
    val title: String,
    val description: String? = null,
    private val subActivity: Class<*>? = null,
    val children: List<EntryPoint>? = null,
    var father: EntryPoint? = null
) {
    init {
        children?.map {
            it.father = this
        }
    }

    fun startSubActivity(context: Context) =
        subActivity?.let {
            context.startActivity(
                Intent(context, it).apply {
                    putExtra("title", title)
                }
            )
        }
}