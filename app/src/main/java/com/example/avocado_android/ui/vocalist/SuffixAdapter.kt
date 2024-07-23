package com.example.avocado_android.ui.vocalist

import android.view.LayoutInflater
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
import com.example.avocado_android.domain.model.local.vocalist.SuffixItem

class SuffixAdapter: BaseAdapter<SuffixItem, ItemVocalistSuffixBinding>(
    BaseDiffCallback(
        areItemsTheSame = {oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = {oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int = R.layout.item_vocalist_suffix

    override fun bind(binding: ItemVocalistSuffixBinding, item: SuffixItem) {
        binding.suffixItem = item
    }

}