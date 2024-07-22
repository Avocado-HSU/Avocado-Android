package com.example.avocado_android.data.remote

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Member

interface MainPageApi {

    // 메인 페이지 정보 가져옴
    @POST("/api/main/{id}")
    suspend fun getMainPage(
        @Path("id") id : Member
    ) // ResponseDTO

    // 최근 검색어 가져옴
    @POST("/api/main/{id}/search/recent")
    suspend fun getRecentSearch(
        @Path("id") id : Member
    ) // ResponseDTO

    // 최근 검색어 가져옴
    @POST("/api/main/{id}/search/{word}")
    suspend fun searchWord(
        @Path("id") id : Member,
        @Path("word") word : Member
    ) // ResponseDTO

}