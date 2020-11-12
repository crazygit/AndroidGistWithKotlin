package com.github.crazygit.demo.androidgistwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.crazygit.demo.androidgistwithkotlin.databinding.EntrypointItemBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint

class MainAdapter(val entryPointClickListener: EntryPointClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var currentEntryPoint: EntryPoint? = null

    inner class ViewHolder(private val itemBinding: EntrypointItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(entryPoint: EntryPoint) {
            itemBinding.entryPointTitle.text = entryPoint.title
            if (entryPoint.description != null) {
                itemBinding.entryPointDescription.text = entryPoint.description
                itemBinding.entryPointDescription.visibility = View.VISIBLE
            } else {
                itemBinding.entryPointDescription.visibility = View.GONE
            }
            itemBinding.root.setOnClickListener {
                entryPointClickListener.onEntryPointClick(entryPoint)
            }
        }
    }

    fun setCurrentEntryPoint(entryPoint: EntryPoint?) {
        currentEntryPoint = entryPoint
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: EntrypointItemBinding =
            EntrypointItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentEntryPoint?.children?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = currentEntryPoint?.children?.size ?: 0

}



