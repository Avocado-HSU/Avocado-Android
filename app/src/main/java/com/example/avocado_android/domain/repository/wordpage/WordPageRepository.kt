package com.example.avocado_android.domain.repository.wordpage

import com.example.avocado_android.domain.model.response.search.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import kotlinx.coroutines.flow.Flow

interface WordPageRepository {
    suspend fun updateLibrary(libraryId : Long) : Flow<UpdateLibraryResponseDto>
    suspend fun wordSearch(id : Long) : Flow<RecentSearchWordResponseDto>
}