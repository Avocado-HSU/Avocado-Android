package com.example.avocado_android.data.source.mainpage

import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.domain.repository.mainpage.MainPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainPageRepositoryImpl @Inject constructor(
    private val dataSource: MainPageDataSource
) : MainPageRepository {

    override suspend fun getMainPage(id: Long) : Flow<MainPageResponseDto> = dataSource.getMainPage(id)
    override suspend fun searchWord(id: Long, word: String) : Flow<SearchWordResponseDto> = dataSource.searchWord(id, word)
    override suspend fun getRecentSearch(id: Long) : Flow<RecentSearchWordResponseDto> = dataSource.getRecentSearch(id)
}