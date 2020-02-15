package com.example.logoquiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.logoquiz.R


class LetterViewAdapter(private val listener: ItemClickListener) : RecyclerView.Adapter<LetterViewHolder>() {
    private val viewDataList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        return LetterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.letter_item, parent, false))
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        holder.onBind(viewDataList[position], ::onClick)
    }

    override fun getItemCount(): Int {
        return viewDataList.size
    }

    fun setItems(list: List<String>) {
        viewDataList.clear()
        viewDataList.addAll(list)
        notifyDataSetChanged()
    }

    private fun onClick(position: Int) {
        listener.onItemClick(viewDataList[position])
    }

    interface ItemClickListener {
        fun onItemClick(letter: String)
    }
}