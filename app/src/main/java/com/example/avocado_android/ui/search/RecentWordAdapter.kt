package com.example.avocado_android.ui.search

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
import com.example.avocado_android.domain.model.search.RecentWordItem

class RecentWordAdapter: BaseAdapter<RecentWordItem, ItemSerachRecentBinding> (
    BaseDiffCallback(
        areItemsTheSame = {oldItem, newItem ->  oldItem.id == newItem.id},
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
) {

    override val layoutId : Int = R.layout.item_recommend_word

    override fun bind(binding: ItemSerachRecentBinding, item: RecentWordItem) {
        binding.searchRecent = item
    }

}


