package com.example.avocado_android.domain.repository.librarypage

import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow

interface LibraryPageRepository {

    suspend fun getLibraryPage(id: Long) : Flow<LibraryPageResponseDto>
    suspend fun searchWord1(id: Long, word: String) : Flow<SearchWordResponseDto>
    suspend fun deleteLibraryWord(libraryId: Int) : Flow<UpdateLibraryResponseDto>
}