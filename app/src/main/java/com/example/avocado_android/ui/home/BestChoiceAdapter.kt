package com.example.avocado_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemHomeBestSearchBinding

class BestChoiceAdapter: ListAdapter<String, BestChoiceAdapter.BestChoiceViewHolder>(
    BestChoiceDiffCallback()
) {

    inner class BestChoiceViewHolder(private val binding: ItemHomeBestSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.itemHomeBestChoiceWordTv.text = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestChoiceViewHolder {
        val binding: ItemHomeBestSearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_home_best_search, parent, false
        )
        return BestChoiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestChoiceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BestChoiceDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}