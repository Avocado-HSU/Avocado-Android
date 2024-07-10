package com.example.avocado_android.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avocado_android.domain.model.RecentWordItem

class SearchViewModel: ViewModel() {
    private val _recentWordList = MutableLiveData<List<RecentWordItem>>()
    val recentWordList: LiveData<List<RecentWordItem>>
        get() = _recentWordList

    init {
        _recentWordList.value = listOf(
            RecentWordItem(0, "wow"),
            RecentWordItem(1, "good"),
            RecentWordItem(2, "problem"),
            RecentWordItem(3, "imminent"),
            RecentWordItem(4, "mundane"),
            RecentWordItem(5, "hello"),
        )
    }

    fun update(item: List<RecentWordItem>) {
        _recentWordList.value = item
    }
}