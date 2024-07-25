package com.example.avocado_android.data.source.search

import com.example.avocado_android.data.source.wordpage.WordPageDataSource
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.domain.repository.search.SearchPageRepository
import com.example.avocado_android.domain.repository.wordpage.WordPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchPageRepositoryImpl @Inject constructor(
    private val dataSource: SearchPageDataSource
) : SearchPageRepository {
    override suspend fun wordSearch(id: Long, word: String): Flow<SearchWordResponseDto> = dataSource.wordSearch(id, word)
}