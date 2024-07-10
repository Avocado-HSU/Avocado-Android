package com.example.avocado_android.ui.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.avocado_android.R
import com.example.avocado_android.databinding.FragmentSearchBinding
import com.example.avocado_android.databinding.ItemSerachRecentBinding
import com.example.avocado_android.domain.model.RecentWordItem

class RecentWordAdapter: ListAdapter<RecentWordItem, RecentWordAdapter.RecentWordViewHolder>(diffUtil) {

    inner class RecentWordViewHolder(private val binding: ItemSerachRecentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecentWordItem) {
            binding.searchRecent = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentWordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSerachRecentBinding>(
            layoutInflater,
            R.layout.item_serach_recent,
            parent,
            false)

        return RecentWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentWordItem>() {

            override fun areItemsTheSame(oldItem: RecentWordItem, newItem: RecentWordItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecentWordItem, newItem: RecentWordItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}


