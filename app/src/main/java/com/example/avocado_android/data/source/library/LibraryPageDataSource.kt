package com.example.avocado_android.data.source.library

import android.util.Log
import com.example.avocado_android.data.remote.LibraryPageApi
import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LibraryPageDataSource @Inject constructor(
    private val libraryPageApi : LibraryPageApi
)  {

    fun getLibraryPage(id: Long) : Flow<LibraryPageResponseDto> = flow {
        val result = libraryPageApi.getLibraryPage(id)
        emit(result)
    }.catch {
        Log.e("GET LibraryPage By LibraryPageResponseDto Failure", it.message.toString())
    }

    fun searchWord1(id: Long, word: String) : Flow<SearchWordResponseDto> = flow {
        val result = libraryPageApi.searchWord1(id, word)
        emit(result)
    }.catch {
        Log.e("GET searchWord1 By SearchWordResponseDto Failure", it.message.toString())
    }

    fun deleteLibraryWord(libraryId: Long) : Flow<UpdateLibraryResponseDto> = flow {
        val result = libraryPageApi.deleteLibraryWord(libraryId)
        emit(result)
    }.catch {
        Log.e("GET deleteLibraryWord By SearchWordResponseDto Failure", it.message.toString())
    }

}
