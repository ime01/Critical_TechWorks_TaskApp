package com.example.criticaltechworkstaskapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.databinding.NewsListItemBinding
import com.example.criticaltechworkstaskapp.domian.model.News


typealias urlListener = (item: News) -> Unit


class NewsAdapter  (val listener: urlListener)  :ListAdapter<News, NewsAdapter.ImageViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)

        return ImageViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)) {
            getItem(it)?.let{item-> listener(item)}
        }

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.binding.apply {

            holder.itemView.apply {
                headlineTitle.text = currentItem.title
                val imageLink = currentItem?.urlToImage

                imageThumbail.load(imageLink){
                    error(R.drawable.ic_baseline_error_outline_24)
                    placeholder(R.drawable.ic_baseline_newspaper_24)
                    crossfade(true)
                    crossfade(1000)
                }
            }
        }
    }

    inner class ImageViewHolder(val binding: NewsListItemBinding, private val listener: (Int)-> Unit): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                listener(adapterPosition)
            }
        }
    }


    interface RowClickListener{
        fun onItemClickListener(car: News)

    }

}