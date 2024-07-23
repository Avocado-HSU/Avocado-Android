package com.example.avocado_android.data.remote

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Member

interface LibraryPageApi {

    // 라이브러리에 단어 20개 이상 넘어가면 항목 삭제
    @DELETE("/api/library/{libraryId}")
    suspend fun deleteLibraryWord(
        @Path("libraryId") libraryId: String
    ) // ResponseDTO

    // 라이브러리 화면 정보 가져옴
    @GET("/api/library/{id}")
    suspend fun getLibraryPage(
        @Path("id") id : Member
    ) // ResponseDTO

    // 라이브러리 화면에서 단어 검색
    @GET("/api/library/{id}/search/{word}")
    suspend fun searchWord1(
        @Path("id") id: Member,
        @Path("word") word: String
    )

}
