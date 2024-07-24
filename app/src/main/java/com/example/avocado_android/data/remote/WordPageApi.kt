package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Member

interface WordPageApi {
    
    // 단어 라이브러리에 등록
    @POST("/api/word/library/{libraryId}")
    suspend fun updateLibrary(
        @Path("libraryId") libraryId : Long
    ) : UpdateLibraryResponseDto

    // 단어장 화면에서 검색
    @GET("/api/word/{id}/search/{word}")
    suspend fun wordSearch(
        @Path("id") id : Long,
        @Path("word") word : String
    ) : SearchWordResponseDto
}