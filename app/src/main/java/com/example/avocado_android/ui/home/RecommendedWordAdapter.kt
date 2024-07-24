package com.example.avocado_android.ui.home

import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemRecommendWordBinding
import com.example.avocado_android.domain.model.local.home.HomeRecommendedItem
import com.example.avocado_android.domain.model.response.main.RecommendWordDto
import com.example.avocado_android.domain.model.response.main.WordDto


class RecommendedWordAdapter : BaseAdapter<WordDto, ItemRecommendWordBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int
        get() = R.layout.item_recommend_word

    override fun bind(binding: ItemRecommendWordBinding, item: WordDto) {
        binding.recommendedData = item
    }
}
