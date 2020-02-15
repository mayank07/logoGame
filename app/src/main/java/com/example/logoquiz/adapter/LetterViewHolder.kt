package com.example.logoquiz.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.letter_item.view.info_text

class LetterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(letter: String, callback: (pos: Int) -> Unit) {
        with(itemView) {
            info_text.text = letter
            this.setOnClickListener {
                callback.invoke(adapterPosition)
            }
        }
    }
}