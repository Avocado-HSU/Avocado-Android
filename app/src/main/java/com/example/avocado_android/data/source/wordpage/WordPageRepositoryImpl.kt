package com.example.avocado_android.data.source.wordpage

import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.domain.repository.wordpage.WordPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordPageRepositoryImpl @Inject constructor(
    private val dataSource: WordPageDataSource
) : WordPageRepository {
    override suspend fun updateLibrary(libraryId: Long): Flow<UpdateLibraryResponseDto> = dataSource.updateLibrary(libraryId)
    override suspend fun wordSearch(id: Long, word: String): Flow<SearchWordResponseDto> = dataSource.wordSearch(id, word)
}