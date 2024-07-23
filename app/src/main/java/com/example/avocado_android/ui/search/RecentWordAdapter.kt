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
import com.example.avocado_android.domain.model.local.search.RecentWordItem
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto

class RecentWordAdapter: BaseAdapter<RecentSearchWordResponseDto, ItemSerachRecentBinding> (
    BaseDiffCallback(
        areItemsTheSame = {oldItem, newItem ->  oldItem == newItem},
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
) {

    override val layoutId : Int = R.layout.item_serach_recent

    override fun bind(binding: ItemSerachRecentBinding, item: RecentSearchWordResponseDto) {
        //binding.searchRecent = item
    }

}


