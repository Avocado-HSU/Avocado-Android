package com.example.avocado_android.domain.repository.mainpage

import com.example.avocado_android.domain.model.request.MainPageRequestDto
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow

interface MainPageRepository {
    suspend fun getMainPage(id: Long, date: MainPageRequestDto) : Flow<MainPageResponseDto>
    suspend fun searchWord(id: Long, word: String) : Flow<SearchWordResponseDto>
    suspend fun getRecentSearch(id: Long) : Flow<RecentSearchWordResponseDto>
}