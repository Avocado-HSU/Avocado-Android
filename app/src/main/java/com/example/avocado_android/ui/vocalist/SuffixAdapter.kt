package com.example.avocado_android.ui.vocalist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemSerachRecentBinding
import com.example.avocado_android.databinding.ItemVocalistSuffixBinding
import com.example.avocado_android.domain.model.vocalist.SuffixItem

class SuffixAdapter: ListAdapter<SuffixItem, SuffixAdapter.SuffixViewHolder>(diffUtil) {

    inner class SuffixViewHolder(private val binding: ItemVocalistSuffixBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentList: SuffixItem) {
            binding.suffixItem = currentList
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuffixViewHolder {
        val binding = DataBindingUtil.inflate<ItemVocalistSuffixBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_vocalist_suffix,
            parent,
            false
        )
        return SuffixViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuffixViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<SuffixItem>() {
            override fun areItemsTheSame(oldItem: SuffixItem, newItem: SuffixItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SuffixItem, newItem: SuffixItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}