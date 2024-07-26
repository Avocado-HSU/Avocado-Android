package com.example.avocado_android.data.source.search

import android.util.Log
import com.example.avocado_android.base.ApiResponse
import com.example.avocado_android.data.remote.SearchPageApi
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class SearchPageDataSource @Inject constructor(
    private val searchPageApi: SearchPageApi
) {
    fun wordSearch(id: Long, word: String): Flow<Response<SearchWordResponseDto>> = flow {
        try {
            val response = searchPageApi.wordSearch(id, word)
            if (response.isSuccessful) {
                emit(response)
            } else {
                emit(Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null, "Unknown error")))
            }
        } catch (exception: Exception) {
            Log.e("GET wordSearch Failure", exception.message.toString())
            emit(Response.error(500, (exception.message ?: "Unknown error").toResponseBody(null)))
        }
    }
}




