package com.example.avocado_android.ui.vocalist

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemSameWordBinding
import com.example.avocado_android.databinding.ItemVocalistAffixBinding
import com.example.avocado_android.domain.model.vocalist.AffixItem
import com.example.avocado_android.domain.model.vocalist.SameWordItem

class AffixAdapter: BaseAdapter<AffixItem, ItemVocalistAffixBinding>(
    BaseDiffCallback(
        areItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override val layoutId: Int = R.layout.item_same_word

    override fun bind(binding: ItemVocalistAffixBinding, item: AffixItem) {
        binding.affixItem = item
    }

}