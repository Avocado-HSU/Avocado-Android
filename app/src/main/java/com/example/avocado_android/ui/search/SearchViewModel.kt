package com.example.avocado_android.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
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

    // 전체 DTO
    private val _searchWordResponseDto = MutableStateFlow(SearchWordResponseDto())
    val searchWordResponseDto: StateFlow<SearchWordResponseDto> get() = _searchWordResponseDto

    // 어원 분리 DTO
    private val _wordEtymologyDto = MutableStateFlow(WordEtymologyDto())
    val wordEtymologyDto: StateFlow<WordEtymologyDto> get() = _wordEtymologyDto

    // 최근 검색어 DTO
    private val _recentWordList = MutableStateFlow(RecentSearchWordResponseDto(emptyList()))
    val recentWordList: StateFlow<RecentSearchWordResponseDto> get() = _recentWordList

    // 어원 분리 DTO Set 함수
    fun setWordEtymologyDto(wordEtymologyDto: WordEtymologyDto) {
        _wordEtymologyDto.value = wordEtymologyDto
    }

    // 최근 검색어
    fun getRecentSearch(id: Long) {
        viewModelScope.launch {
            try {
                mainPageRepository.getRecentSearch(id).collect {
                    _recentWordList.value = it
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel getRecentSearch Error", e.message.toString())
            }
        }
    }

    // 단어 검색하면 단어장 화면에 나옴
    fun wordSearch(id: Long, word: String) {
        viewModelScope.launch {
            try {
                searchPageRepository.wordSearch(id, word).collect {
                    _searchWordResponseDto.value = it
                    Log.d("SearchViewModel", "_affixItemList : $it")
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel wordSearch Error", e.message.toString())
            }
        }
    }

    // 라이브러리에 단어 등록/삭제
    fun updateLibrary(libraryId: Long) {
        viewModelScope.launch {
            try {
                wordPageRepository.updateLibrary(libraryId).collect(){
                    Log.d("WordListFragment", "it : $it")
                }
            } catch (e: Exception) {
                Log.e("SearchViewModel updateLibrary Error", e.message.toString())
            }
        }
    }


}