package com.github.crazygit.demo.androidgistwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.crazygit.demo.androidgistwithkotlin.databinding.MenuItemBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.Menu

class MainAdapter(val menuClickListener: MenuClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var currentMenu: Menu? = null

    inner class ViewHolder(private val itemBinding: MenuItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(menu: Menu) {
            itemBinding.menuTitle.text = menu.title
            if (menu.description != null) {
                itemBinding.menuDescription.text = menu.description
                itemBinding.menuDescription.visibility = View.VISIBLE
            } else {
                itemBinding.menuDescription.visibility = View.GONE
            }
            itemBinding.root.setOnClickListener {
                menuClickListener.onMenuClick(menu)
            }
        }
    }

    fun setCurrentMenu(menu: Menu?) {
        currentMenu = menu
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: MenuItemBinding =
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentMenu?.children?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = currentMenu?.children?.size ?: 0

}



