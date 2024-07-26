package com.example.avocado_android.ui.vocalist

import android.util.Log
import android.view.View
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemWordListEtymologyBinding
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto

class EtymologyAdapter : BaseAdapter<WordEtymologyDto, ItemWordListEtymologyBinding> (
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem.etymology == newItem.etymology },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int get() = R.layout.item_word_list_etymology

    override fun bind(binding: ItemWordListEtymologyBinding, item: WordEtymologyDto) {
        if (item.etymology.isNullOrEmpty() || item.etymology!!.contains("없음")) {
            binding.root.visibility = View.GONE
        } else {
            binding.root.visibility = View.VISIBLE
            binding.wordEtymologyDto = item
        }
    }

}