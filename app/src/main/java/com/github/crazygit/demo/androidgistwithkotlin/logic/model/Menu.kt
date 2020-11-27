package com.github.crazygit.demo.androidgistwithkotlin.logic.model

import android.content.Context
import android.content.Intent
import android.widget.Toast

class Menu(
    var title: String? = "untitled",
    var description: String? = null,
    var subActivity: Class<*>? = null,
    var children: ArrayList<Menu>? = null,
    var father: Menu? = null
) {

    fun addChild(child: Menu) {
        if (children == null) {
            children = ArrayList()
        }
        children?.apply {
            child.father = this@Menu
            add(child)
        }
    }

    fun startSubActivity(context: Context) =
        subActivity?.let {
            context.startActivity(
                Intent(context, it).apply {
                    putExtra("title", title)
                }
            )
        } ?: run {
            Toast.makeText(context, "Already hit the bottom level", Toast.LENGTH_SHORT).show()
        }

}

fun menu(block: Menu.() -> Unit): Menu = Menu().apply(block)

fun Menu.subMenu(block: Menu.() -> Unit) {
    val child = Menu().apply(block)
    addChild(child)
}


