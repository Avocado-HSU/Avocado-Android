package com.example.avocado_android.ui.search

import android.util.Log
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

class RecentWordAdapter(
    private val onItemClick: (String) -> Unit
): BaseAdapter<String, ItemSerachRecentBinding> (
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId : Int = R.layout.item_serach_recent

    override fun bind(binding: ItemSerachRecentBinding, item: String) {
        binding.recentSearch = item
        Log.d("recentSearch", "recentSearch: $item")

        // 클릭 리스너 설정
        binding.root.setOnClickListener {
            onItemClick(item)
        }
    }
}