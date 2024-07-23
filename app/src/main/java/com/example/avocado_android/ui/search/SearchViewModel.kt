package com.example.avocado_android.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.domain.model.local.search.RecentWordItem
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.repository.mainpage.MainPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainPageRepository: MainPageRepository
) : ViewModel() {
    private val _recentWordList = MutableStateFlow(RecentSearchWordResponseDto(emptyList()))
    val recentWordList: StateFlow<RecentSearchWordResponseDto> get() = _recentWordList

//    init {
//        _recentWordList.value = listOf(
//            RecentWordItem(0, "wow"),
//            RecentWordItem(1, "good"),
//            RecentWordItem(2, "problem"),
//            RecentWordItem(3, "imminent"),
//            RecentWordItem(4, "mundane"),
//            RecentWordItem(5, "hello"),
//        )
//    }

    fun getRecentSearch(id: Long) {
//        viewModelScope.launch {
//            try {
//                mainPageRepository.getRecentSearch(id).collect {
//                    _recentWordList.value = it
//                }
//            }
//        }
    }

    fun update(item: List<RecentWordItem>) {
       // _recentWordList.value = item
    }
}