package com.example.avocado_android.domain.repository.mainpage

import com.example.avocado_android.domain.model.request.MainPageRequestDto
import com.example.avocado_android.domain.model.response.search.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import kotlinx.coroutines.flow.Flow

interface MainPageRepository {
    suspend fun getMainPage(id: Long, date: MainPageRequestDto) : Flow<MainPageResponseDto>
    suspend fun getRecentSearch(id: Long) : Flow<RecentSearchWordResponseDto>
}