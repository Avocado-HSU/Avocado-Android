package com.example.avocado_android.data.source.mainpage

import android.util.Log
import com.example.avocado_android.data.remote.MainPageApi
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainPageDataSource @Inject constructor(
    private val mainPageApi: MainPageApi
) {

    fun getMainPage(id: Long) : Flow<MainPageResponseDto> = flow {
        val result = mainPageApi.getMainPage(id)
        emit(result)
    }.catch {
        Log.e("GET getMainPage By MainPageResponseDto Failure", it.message.toString()) }

    fun searchWord(id: Long, word: String) : Flow<SearchWordResponseDto> = flow {
        val result = mainPageApi.searchWord(id, word)
        emit(result)
    }.catch {
        Log.e("GET searchWord By SearchWordResponseDto Failure", it.message.toString()) }

    fun getRecentSearch(id: Long) : Flow<RecentSearchWordResponseDto> = flow {
        val result = mainPageApi.getRecentSearch(id)
        emit(result)
    }.catch {
        Log.e("GET getRecentSearch By RecentSearchWordResponseDto Failure", it.message.toString()) }

}