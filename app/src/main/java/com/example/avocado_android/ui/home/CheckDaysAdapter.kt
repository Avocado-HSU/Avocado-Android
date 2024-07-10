package com.example.avocado_android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemHomeCheckedDaysBinding
import com.example.avocado_android.domain.model.HomeDaysItem

class CheckDaysAdapter : ListAdapter<HomeDaysItem, CheckDaysAdapter.HomeDaysViewHolder>(HomeDaysDiffCallback()) {

    inner class HomeDaysViewHolder(private val binding: ItemHomeCheckedDaysBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeDaysItem) {
            binding.checkedDayDate = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDaysViewHolder {
        val binding: ItemHomeCheckedDaysBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_home_checked_days, parent, false
        )
        return HomeDaysViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeDaysViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeDaysDiffCallback : DiffUtil.ItemCallback<HomeDaysItem>() {
        override fun areItemsTheSame(oldItem: HomeDaysItem, newItem: HomeDaysItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeDaysItem, newItem: HomeDaysItem): Boolean {
            return oldItem == newItem
        }
    }
}