package com.example.codechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.data.entities.GraphicItem

class GraphicItemListAdapter() :
    ListAdapter<GraphicItem, GraphicItemListAdapter.GraphicItemViewHolder>(GraphicItemListAdapter) {

    inner class GraphicItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvGraphic: TextView = view.findViewById(R.id.textViewGraphic)

        fun bind(
            item: GraphicItem
        ) {
            tvGraphic.text = item.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraphicItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_graphic, parent, false)
        return GraphicItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GraphicItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object : DiffUtil.ItemCallback<GraphicItem>() {

        override fun areItemsTheSame(oldItem: GraphicItem, newItem: GraphicItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GraphicItem, newItem: GraphicItem): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.totalAnimais == newItem.totalAnimais &&
                    oldItem.primeiraOrdenha == newItem.primeiraOrdenha &&
                    oldItem.segundaOrdenha == newItem.segundaOrdenha &&
                    oldItem.totalLitros == newItem.totalLitros &&
                    oldItem.media == newItem.media &&
                    oldItem.data == newItem.data
        }

    }

}