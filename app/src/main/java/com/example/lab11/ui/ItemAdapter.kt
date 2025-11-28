package com.example.lab11.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab11.data.Item

class ItemAdapter(private val onClick: (Item) -> Unit) : RecyclerView.Adapter<ItemAdapter.VH>() {
    private val items = mutableListOf<Item>()

    fun submit(list: List<Item>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(android.R.id.text1) // Используем системный ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.text.text = item.title
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}