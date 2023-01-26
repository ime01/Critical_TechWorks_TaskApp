package com.example.criticaltechworkstaskapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.criticaltechworkstaskapp.domian.model.News


class NewsDiffCallback : DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }
    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}