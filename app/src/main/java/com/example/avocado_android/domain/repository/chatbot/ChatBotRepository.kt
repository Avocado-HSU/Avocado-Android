package com.example.avocado_android.domain.repository.chatbot

import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import kotlinx.coroutines.flow.Flow

interface ChatBotRepository {
    suspend fun requestChatbot(requestType: String, word: String) : Flow<ChatBotResponseDto>
}