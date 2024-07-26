package com.example.avocado_android.ui.vocalist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemSerachRecentBinding
import com.example.avocado_android.databinding.ItemVocalistSuffixBinding
import com.example.avocado_android.databinding.ItemWordListSuffixBinding
import com.example.avocado_android.domain.model.local.vocalist.SuffixItem
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto

class SuffixAdapter: BaseAdapter<WordEtymologyDto, ItemWordListSuffixBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem== newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int = R.layout.item_word_list_suffix

    override fun bind(binding: ItemWordListSuffixBinding, item: WordEtymologyDto) {
        if (item.suffix.isNullOrEmpty() && item.suffix!!.contains("없음")) {
            binding.root.visibility = View.GONE
        } else {
            binding.root.visibility = View.VISIBLE
            binding.wordEtymologyDto = item
        }
    }

}