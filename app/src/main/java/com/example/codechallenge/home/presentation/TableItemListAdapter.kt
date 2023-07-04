package com.example.codechallenge.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.home.data.entities.TableItem

class TableItemListAdapter() :
    ListAdapter<TableItem, TableItemListAdapter.TableItemViewHolder>(TableItemListAdapter) {

    inner class TableItemViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        val tvAnimal: TextView = view.findViewById(R.id.tvAnimalRow)
        val tvBaia: TextView = view.findViewById(R.id.tvBaiaRow)
        val tvDel: TextView = view.findViewById(R.id.tvDelRow)
        val tvPrimeiro: TextView = view.findViewById(R.id.tvPrimeiroRow)
        val tvSegundo: TextView = view.findViewById(R.id.tvSegundoRow)
        val tvTotal: TextView = view.findViewById(R.id.tvTotalRow)

        fun bind(
            item: TableItem
        ) {
            tvAnimal.text = item.animal.toString()
            tvBaia.text = item.baia.toString()
            tvDel.text = item.del.toString()
            tvPrimeiro.text = item.primeiro.toString()
            tvSegundo.text = item.segundo.toString()
            tvTotal.text = item.total.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_table, parent, false)
        return TableItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TableItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object : DiffUtil.ItemCallback<TableItem>() {

        override fun areItemsTheSame(oldItem: TableItem, newItem: TableItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TableItem, newItem: TableItem): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.microChip == newItem.microChip &&
                    oldItem.animal == newItem.animal &&
                    oldItem.nome == newItem.nome &&
                    oldItem.dataParto == newItem.dataParto &&
                    oldItem.baia == newItem.baia &&
                    oldItem.primeiro == newItem.primeiro &&
                    oldItem.segundo == newItem.segundo &&
                    oldItem.total == newItem.total &&
                    oldItem.dataControle == newItem.dataControle &&
                    oldItem.del == newItem.del &&
                    oldItem.obs == newItem.obs
        }

    }

}