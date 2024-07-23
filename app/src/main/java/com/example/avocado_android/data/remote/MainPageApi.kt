package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.request.MainPageRequestDto
import com.example.avocado_android.domain.model.response.RecentSearchWordResponseDto
import com.example.avocado_android.domain.model.response.main.MainPageResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Member

interface MainPageApi {

    // 메인 페이지 정보 가져옴
    @POST("/api/main/{id}")
    suspend fun getMainPage(
        @Path("id") id : Long,
        @Body data : MainPageRequestDto
    ) : MainPageResponseDto

    // 최근 검색어 가져옴
    @POST("/api/main/{id}/search/{word}")
    suspend fun searchWord(
        @Path("id") id : Long,
        @Path("word") word : String
    ) : SearchWordResponseDto

    // 최근 검색어 가져옴
    @POST("/api/main/{id}/search/recent")
    suspend fun getRecentSearch(
        @Path("id") id : Long
    ) : RecentSearchWordResponseDto

}