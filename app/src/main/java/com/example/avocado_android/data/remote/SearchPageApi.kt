package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchPageApi {
    // 단어장 화면에서 검색
    @GET("/api/search/{id}/{word}")
    suspend fun wordSearch(
        @Path("id") id : Long,
        @Path("word") word : String
    ) : SearchWordResponseDto
}