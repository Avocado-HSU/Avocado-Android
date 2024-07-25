package com.example.avocado_android.data.source.library

import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import com.example.avocado_android.domain.repository.librarypage.LibraryPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LibraryPageRepositoryImpl @Inject constructor (
    private val dataSource: LibraryPageDataSource
) : LibraryPageRepository {

    override suspend fun getLibraryPage(id: Long): Flow<LibraryPageResponseDto> = dataSource.getLibraryPage(id)
    override suspend fun searchWord1(id: Long, word: String): Flow<SearchWordResponseDto> = dataSource.searchWord1(id, word)
    override suspend fun deleteLibraryWord(libraryId: Long): Flow<UpdateLibraryResponseDto> = dataSource.deleteLibraryWord(libraryId)
}