package com.example.avocado_android.data.source.chatbot

import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatBotRepositoryImpl @Inject constructor(
    private val dataSource: ChatBotDataSource
) : ChatBotRepository {
    override suspend fun requestChatbot(
        requestType: String,
        word: String
    ): Flow<ChatBotResponseDto> = dataSource.requestChatbot(requestType, word)
}