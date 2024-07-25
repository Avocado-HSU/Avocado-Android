package com.example.avocado_android.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.domain.model.local.search.RecentWordItem
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.domain.repository.mainpage.MainPageRepository
import com.example.avocado_android.domain.repository.search.SearchPageRepository
import com.example.avocado_android.domain.repository.wordpage.WordPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainPageRepository: MainPageRepository,
    private val wordPageRepository: WordPageRepository,
    private val searchPageRepository: SearchPageRepository
) : ViewModel() {

    /* private val _suffixItemList = MutableLiveData<List<SuffixItem>>()
    val suffixItem: LiveData<List<SuffixItem>> get() = _suffixItemList

    private val _sameWordItemList = MutableLiveData<List<SameWordItem>>()
    val sameWordItem: LiveData<List<SameWordItem>> get() = _sameWordItemList */

    private val _searchWordResponseDto = MutableStateFlow(SearchWordResponseDto())
    val searchWordResponseDto: StateFlow<SearchWordResponseDto> get() = _searchWordResponseDto

    private val _recentWordList = MutableStateFlow(RecentSearchWordResponseDto(emptyList()))
    val recentWordList: StateFlow<RecentSearchWordResponseDto> get() = _recentWordList

    fun getRecentSearch(id: Long) {
//        viewModelScope.launch {
//            try {
//                mainPageRepository.getRecentSearch(id).collect {
//                    _recentWordList.value = it
//                }
//            }
//        }
    }

    fun wordSearch(id: Long, word: String) {
        viewModelScope.launch {
            try {
                searchPageRepository.wordSearch(id, word).collect {
                    _searchWordResponseDto.value = it
                    Log.d("SearchViewModel", "_affixItemList : $it")
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel Error", e.message.toString())
            }
        }
    }


}