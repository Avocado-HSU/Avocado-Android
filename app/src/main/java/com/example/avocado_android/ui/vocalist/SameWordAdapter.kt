package com.example.avocado_android.ui.vocalist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemSameWordBinding
import com.example.avocado_android.domain.model.vocalist.SameWordItem

class SameWordAdapter: ListAdapter<SameWordItem, SameWordAdapter.SameWordViewHolder>(diffUtil) {

    inner class SameWordViewHolder(val binding: ItemSameWordBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentList: SameWordItem) {
            binding.sameWordItem = currentList
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SameWordViewHolder {
        val binding = DataBindingUtil.inflate<ItemSameWordBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_same_word,
            parent,
            false
        )
        return SameWordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SameWordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SameWordItem>() {
            override fun areItemsTheSame(oldItem: SameWordItem, newItem: SameWordItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SameWordItem, newItem: SameWordItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}