package com.example.avocado_android.domain.repository.search

import com.example.avocado_android.base.ApiResponse
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface SearchPageRepository {
    suspend fun wordSearch(id: Long, word: String): Flow<Response<SearchWordResponseDto>>
}