package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WordPageApi {
    
    // 단어 라이브러리에 등록/삭제 (토글 방식)
    @POST("/api/word/library/{libraryId}")
    suspend fun updateLibrary(
        @Path("libraryId") libraryId : Long
    ) : UpdateLibraryResponseDto

    // 단어장 화면에서 검색
    @GET("/api/word/{id}/search/")
    suspend fun wordSearch(
        @Path("id") id : Long,
    ) : RecentSearchWordResponseDto
}