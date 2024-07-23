package com.example.avocado_android.ui.vocalist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avocado_android.domain.model.local.vocalist.AffixItem
import com.example.avocado_android.domain.model.local.vocalist.SameWordItem
import com.example.avocado_android.domain.model.local.vocalist.SuffixItem

class VocaListViewModel: ViewModel() {
    private val _suffixItemList = MutableLiveData<List<SuffixItem>>()
    val suffixItem: LiveData<List<SuffixItem>> get() = _suffixItemList

    private val _sameWordItemList = MutableLiveData<List<SameWordItem>>()
    val sameWordItem: LiveData<List<SameWordItem>> get() = _sameWordItemList

    private val _affixItemList = MutableLiveData<List<AffixItem>>()
    val affixItem: LiveData<List<AffixItem>> get() = _affixItemList

    init {
        _suffixItemList.value = listOf(
            SuffixItem(0, "-tion (명사)"),
            SuffixItem(1, "-ary (장소 또는 물건)"),
        )

        _sameWordItemList.value = listOf(
            SameWordItem(0, "Technology"),
            SameWordItem(1, "Nature"),
            SameWordItem(2, "Food"),
            SameWordItem(3, "Responsive"),
        )

        _affixItemList.value = listOf(
            AffixItem(0, "Technology"),
            AffixItem(0, "Nature"),
            AffixItem(1, "Food"),
            AffixItem(2, "Responsive"),
        )
    }

    fun update() {

    }

}