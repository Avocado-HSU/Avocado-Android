package com.example.avocado_android.domain.model.local.library

import android.widget.AdapterView
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemHomeBestSearchBinding
import com.example.avocado_android.databinding.ItemLibraryWordCardBinding
import com.example.avocado_android.domain.model.response.library.LibraryWordDto


class LibraryCardAdapter() : BaseAdapter<LibraryWordDto, ItemLibraryWordCardBinding>(
    BaseDiffCallback(
        itemsTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    private var listener: OnLibraryClickListener? = null
    override val layoutId: Int
        get() = R.layout.item_library_word_card

    fun setListener(listener: OnLibraryClickListener){
        this.listener = listener
    }

    override fun bind(binding: ItemLibraryWordCardBinding, item: LibraryWordDto) {
        binding.libraryData = item
        binding.itemLibraryWordCardNextIb.setOnClickListener {
            listener?.onItemClick(item)
        }
    }
}


interface OnLibraryClickListener {
    fun onItemClick(item: LibraryWordDto)
}