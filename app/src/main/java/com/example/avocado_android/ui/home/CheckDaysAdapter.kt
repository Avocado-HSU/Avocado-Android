package com.example.avocado_android.ui.home

import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemHomeCheckedDaysBinding
import com.example.avocado_android.domain.model.local.home.HomeDaysItem


class CheckDaysAdapter : BaseAdapter<HomeDaysItem, ItemHomeCheckedDaysBinding>(
    BaseDiffCallback(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_home_checked_days

    override fun bind(binding: ItemHomeCheckedDaysBinding, item: HomeDaysItem) {
        binding.checkedDayDate = item
    }
}