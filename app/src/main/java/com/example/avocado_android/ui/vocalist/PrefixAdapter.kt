package com.example.avocado_android.ui.vocalist

import android.util.Log
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemWordListPrefixBinding
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto

class PrefixAdapter: BaseAdapter<WordEtymologyDto, ItemWordListPrefixBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int = R.layout.item_word_list_prefix

    override fun bind(binding: ItemWordListPrefixBinding, item: WordEtymologyDto) {
       binding.wordEtymologyDto = item
        Log.d("PrefixAdapter", "PrefixAdapter: $item")
    }

}