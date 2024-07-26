package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.request.MainPageRequestDto
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.search.SearchWordResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainPageApi {

    // 메인 페이지 정보 가져옴
    @POST("/api/main/{id}")
    suspend fun getMainPage(
        @Path("id") id : Long,
        @Body data : MainPageRequestDto
    ) : MainPageResponseDto

    // 검색 페이지로 이동
    @GET("/api/word/{id}/search")
    suspend fun getRecentSearch(
        @Path("id") id : Long,
    ) : RecentSearchWordResponseDto
}