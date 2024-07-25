package com.example.avocado_android.data.source.search

import android.util.Log
import com.example.avocado_android.data.remote.SearchPageApi
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPageDataSource @Inject constructor(
    private val searchPageApi: SearchPageApi
) {
    fun wordSearch(id : Long, word: String) : Flow<SearchWordResponseDto> = flow {
        val result = searchPageApi.wordSearch(id, word)
        emit(result)
    }.catch {
        Log.e("GET wordSearch By SearchWordResponseDto Failure", it.message.toString())
    }
}