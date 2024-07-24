package com.example.avocado_android.ui.home

import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemHomeBestSearchBinding


class BestChoiceAdapter : BaseAdapter<String, ItemHomeBestSearchBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_home_best_search

    override fun bind(binding: ItemHomeBestSearchBinding, item: String) {
        binding.itemHomeBestChoiceWordTv.text = item
    }
}
