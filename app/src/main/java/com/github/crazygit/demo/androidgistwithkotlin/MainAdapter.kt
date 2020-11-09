package com.github.crazygit.demo.androidgistwithkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.crazygit.demo.androidgistwithkotlin.databinding.EntrypointItemBinding
import com.github.crazygit.demo.androidgistwithkotlin.logic.model.EntryPoint

class MainAdapter(private val entryPointList: List<EntryPoint>, private val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    inner class ViewHolder(private val itemBinding: EntrypointItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(entryPoint: EntryPoint) {
            itemBinding.name.text = entryPoint.name
            itemBinding.name.setOnClickListener {
                context.startActivity(
                    Intent(context, entryPoint.activity)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding: EntrypointItemBinding =
            EntrypointItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entryPoint = entryPointList[position]
        holder.bind(entryPoint)
    }

    override fun getItemCount(): Int = entryPointList.size

}



