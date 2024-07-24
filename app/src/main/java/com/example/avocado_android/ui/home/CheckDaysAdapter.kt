package com.example.avocado_android.ui.home

import android.widget.ImageView
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemHomeCheckedDaysBinding

import androidx.databinding.BindingAdapter
import com.example.avocado_android.domain.model.local.home.HomeDaysItem

class CheckDaysAdapter : BaseAdapter<HomeDaysItem, ItemHomeCheckedDaysBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_home_checked_days

    override fun bind(binding: ItemHomeCheckedDaysBinding, item: HomeDaysItem) {
        binding.checkedDayDate = item
        binding.executePendingBindings()
    }
}

@BindingAdapter("app:imageRes")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}