package com.example.avocado_android.data.remote

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Member

interface WordPageApi {

    // 단어 라이브러리에 등록
    @POST("/api/word/library/{libraryId}")
    suspend fun updateLibrary(
        @Path("libraryId") libraryId : String
    ) // ResponseDTO

    // 단어장 화면에서 검색
    @POST("/api/word/{id}/search/{word}")
    suspend fun wordSearch(
        @Path("id") id : Member,
        @Path("word") word : String
    ) // ResponseDTO

}