package com.example.avocado_android.data.source.wordpage

import android.util.Log
import com.example.avocado_android.data.remote.WordPageApi
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WordPageDataSource @Inject constructor(
    private val wordPageApi: WordPageApi
) {
    fun updateLibrary(libraryId : Long) : Flow<UpdateLibraryResponseDto> = flow {
        val result = wordPageApi.updateLibrary(libraryId)
        emit(result)
    }.catch {
        Log.e("POST updateLibrary By UpdateLibraryResponseDto Failure", it.message.toString())
    }

    fun wordSearch(id : Long, word: String) : Flow<SearchWordResponseDto> = flow {
        val result = wordPageApi.wordSearch(id, word)
        emit(result)
    }.catch {
        Log.e("POST wordSearch By SearchWordResponseDto Failure", it.message.toString())
    }
}