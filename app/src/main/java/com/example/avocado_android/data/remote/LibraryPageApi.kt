package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.model.response.library.UpdateLibraryResponseDto
import com.example.avocado_android.domain.model.response.main.SearchWordResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.reflect.Member

interface LibraryPageApi {

    // 라이브러리 화면 정보 가져옴
    @GET("/api/library/{id}")
    suspend fun getLibraryPage(
        @Path("id") id : Long
    ) : LibraryPageResponseDto

    // 라이브러리 화면에서 단어 검색
    @GET("/api/library/{id}/search/{word}")
    suspend fun searchWord1(
        @Path("id") id: Long,
        @Path("word") word: String
    ) : SearchWordResponseDto

    // 라이브러리에 단어 20개 이상 넘어가면 항목 삭제
    @DELETE("/api/library/{libraryId}")
    suspend fun deleteLibraryWord(
        @Path("libraryId") libraryId: Int
    ) : UpdateLibraryResponseDto

}
