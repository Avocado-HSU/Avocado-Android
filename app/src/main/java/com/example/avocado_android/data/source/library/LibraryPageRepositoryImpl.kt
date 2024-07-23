package com.example.avocado_android.data.source.library

import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import com.example.avocado_android.domain.repository.library.LibraryPageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LibraryPageRepositoryImpl @Inject constructor (
    private val dataSource: LibraryPageDataSource
) : LibraryPageRepository {

    override suspend fun getLibraryPage(id: Long): Flow<LibraryPageResponseDto> = dataSource.getLibraryPage(id)
    override suspend fun searchWord1(id: Long, word: String): Flow<SearchWordResponseDto> = dataSource.searchWord1(id, word)
    override suspend fun deleteLibraryWord(libraryId: Int): Flow<UpdateLibraryResponseDto> = dataSource.deleteLibraryWord(libraryId)
}