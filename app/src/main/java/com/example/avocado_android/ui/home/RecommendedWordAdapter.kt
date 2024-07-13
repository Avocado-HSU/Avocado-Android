package com.example.avocado_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemRecommendWordBinding
import com.example.avocado_android.domain.model.home.HomeRecommendedItem

class RecommendedWordAdapter : ListAdapter<HomeRecommendedItem, RecommendedWordAdapter.RecommendedWordViewHolder>(HomeDaysDiffCallback()) {

    inner class RecommendedWordViewHolder(private val binding: ItemRecommendWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeRecommendedItem) {
            binding.recommendedData = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedWordViewHolder {
        val binding: ItemRecommendWordBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_recommend_word, parent, false
        )
        return RecommendedWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendedWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeDaysDiffCallback : DiffUtil.ItemCallback<HomeRecommendedItem>() {
        override fun areItemsTheSame(oldItem: HomeRecommendedItem, newItem: HomeRecommendedItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeRecommendedItem, newItem: HomeRecommendedItem): Boolean {
            return oldItem == newItem
        }
    }
}