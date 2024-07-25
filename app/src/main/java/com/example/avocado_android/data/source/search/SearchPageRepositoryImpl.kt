package com.example.avocado_android.data.source.search

import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import com.example.avocado_android.domain.repository.search.SearchPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPageRepositoryImpl @Inject constructor(
    private val dataSource: SearchPageDataSource
) : SearchPageRepository {
    override suspend fun wordSearch(id: Long, word: String): Flow<SearchWordResponseDto> = dataSource.wordSearch(id, word)
}