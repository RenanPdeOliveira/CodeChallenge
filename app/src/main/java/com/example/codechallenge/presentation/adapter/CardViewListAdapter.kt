package com.example.codechallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.data.entities.CardItem

class CardViewListAdapter() :
    ListAdapter<CardItem, CardViewListAdapter.CardViewHolder>(CardViewListAdapter) {

    inner class CardViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.textViewTitleGraphic)
        val tvNum: TextView = view.findViewById(R.id.textViewNumGraphic)

        fun bind(
            item: CardItem
        ) {
            tvTitle.text = item.title
            tvNum.text = item.num
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object : DiffUtil.ItemCallback<CardItem>() {

        override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.num == newItem.num
        }

    }

}