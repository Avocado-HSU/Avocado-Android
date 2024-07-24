package com.example.avocado_android.ui.vocalist

import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemWordListAffixBinding
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto

class AffixAdapter: BaseAdapter<SearchWordResponseDto, ItemWordListAffixBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int = R.layout.item_word_list_affix

    override fun bind(binding: ItemWordListAffixBinding, item: SearchWordResponseDto) {
        binding.searchWordResponseDto = item
    }

}