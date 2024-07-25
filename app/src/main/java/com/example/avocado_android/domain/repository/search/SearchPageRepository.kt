package com.example.avocado_android.domain.repository.search

import com.example.avocado_android.domain.model.home.MemberDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow

interface SearchPageRepository {
    suspend fun wordSearch(id: Long, word: String): Flow<SearchWordResponseDto>
}