package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.ChatBotResponseDto
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatBotApi {
    // 챗봇에게 요청하고 응답받기
    @POST("/api/chat/api/request/{request}/{word}")
    suspend fun requestChatbot(
        @Path("requestType") requestType: String,
        @Path("word") word: String
    )  : ChatBotResponseDto
}