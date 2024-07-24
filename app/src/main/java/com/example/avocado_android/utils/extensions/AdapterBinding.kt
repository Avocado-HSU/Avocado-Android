package com.example.avocado_android.utils.extensions

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.avocado_android.domain.model.local.home.HomeDaysItem
import com.example.avocado_android.domain.model.local.search.RecentWordItem
import com.example.avocado_android.domain.model.local.vocalist.AffixItem
import com.example.avocado_android.ui.home.CheckDaysAdapter
import com.example.avocado_android.ui.search.RecentWordAdapter
import com.example.avocado_android.ui.vocalist.AffixAdapter

object AdapterBinding {

    // api 완성되면 다시 작성
//    @JvmStatic
//    @BindingAdapter("app:affix")
//    fun bindData(recyclerView: RecyclerView, data: LiveData<List<AffixItem>>?) {
//        if (recyclerView.adapter == null) {
//            val adapter = AffixAdapter()
//            recyclerView.adapter = adapter
//        }
//        val myAdapter = recyclerView.adapter as AffixAdapter
//        data?.observe(recyclerView.context as LifecycleOwner) { items ->
//            myAdapter.submitList(items)
//        }
//    }

    @JvmStatic
    @BindingAdapter("app:listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: LiveData<List<HomeDaysItem?>>?) {
        if (recyclerView.adapter == null) {
            val adapter = CheckDaysAdapter()
            recyclerView.adapter = adapter
        }
        val myAdapter = recyclerView.adapter as CheckDaysAdapter
        data?.observe(recyclerView.context as LifecycleOwner) { items ->
            myAdapter.submitList(items)
        }
    }

    @JvmStatic
    @BindingAdapter("app:items")
    fun bindSearchRecyclerView(recyclerView: RecyclerView, data: LiveData<List<RecentWordItem?>>?) {
        if (recyclerView.adapter == null) {
            val adapter = RecentWordAdapter()
            recyclerView.adapter = adapter
        }
        val myAdapter = recyclerView.adapter as RecentWordAdapter
        data?.observe(recyclerView.context as LifecycleOwner) { items ->
           // myAdapter.submitList(items)
        }
    }
}