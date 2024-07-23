package com.example.avocado_android.data.remote

import retrofit2.http.POST
import retrofit2.http.Query

interface ChatBotApi {
    // 챗봇에게 요청하고 응답받기
    @POST("/api/chat/api/request")
    suspend fun requestChatbot(
        @Query("requestType") requestType: String,
        @Query("word") word: String
    )   // ResponseDTO
}