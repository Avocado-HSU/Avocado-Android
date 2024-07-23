package com.example.avocado_android.domain.model.local.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.databinding.ItemLibraryWordCardBinding

class LibraryCardAdapter : ListAdapter<LibraryWordCard, LibraryCardAdapter.LibraryCardViewHolder>(
    LibraryCardDiffCallback()
) {

    inner class LibraryCardViewHolder(private val binding: ItemLibraryWordCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LibraryWordCard) {
            binding.libraryWordCardData = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryCardViewHolder {
        val binding: ItemLibraryWordCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_library_word_card, parent, false
        )
        return LibraryCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LibraryCardDiffCallback : DiffUtil.ItemCallback<LibraryWordCard>() {
        override fun areItemsTheSame(oldItem: LibraryWordCard, newItem: LibraryWordCard): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LibraryWordCard, newItem: LibraryWordCard): Boolean {
            return oldItem == newItem
        }
    }
}