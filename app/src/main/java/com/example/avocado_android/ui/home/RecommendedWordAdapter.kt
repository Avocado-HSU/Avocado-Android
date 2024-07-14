package com.example.avocado_android.ui.home

import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemRecommendWordBinding
import com.example.avocado_android.domain.model.home.HomeRecommendedItem


class RecommendedWordAdapter : BaseAdapter<HomeRecommendedItem, ItemRecommendWordBinding>(
    BaseDiffCallback(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_recommend_word

    override fun bind(binding: ItemRecommendWordBinding, item: HomeRecommendedItem) {
        binding.recommendedData = item
    }
}
