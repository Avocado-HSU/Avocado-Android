package com.example.avocado_android.ui.vocalist

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemVocalistAffixBinding
import com.example.avocado_android.domain.model.vocalist.AffixItem

class AffixAdapter: ListAdapter<AffixItem, AffixAdapter.AffixViewHolder>(diffUtil) {

    inner class AffixViewHolder(private val binding: ItemVocalistAffixBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(currentList: AffixItem) {
                binding.affixItem = currentList
                binding.executePendingBindings()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffixViewHolder {
        val binding = DataBindingUtil.inflate<ItemVocalistAffixBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_vocalist_affix,
            parent,
            false
        )
        return AffixViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AffixViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AffixItem>() {
            override fun areItemsTheSame(oldItem: AffixItem, newItem: AffixItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AffixItem, newItem: AffixItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}