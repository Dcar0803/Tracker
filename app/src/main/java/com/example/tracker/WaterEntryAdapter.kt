// WaterEntryAdapter.kt
package com.example.tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterEntryAdapter(var entries: MutableList<WaterEntry>) :
    RecyclerView.Adapter<WaterEntryAdapter.EntryViewHolder>() {

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_water_entry, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.dateTextView.text = entry.date
        holder.amountTextView.text = "${entry.amount} ml"
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}