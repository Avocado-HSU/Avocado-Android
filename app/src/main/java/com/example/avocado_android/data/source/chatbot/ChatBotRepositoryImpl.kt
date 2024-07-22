package com.example.avocado_android.data.source.chatbot

import com.example.avocado_android.domain.repository.ChatBotRepository
import javax.inject.Inject

class ChatBotRepositoryImpl @Inject constructor(
    private val dataSource: ChatBotDataSource
) : ChatBotRepository {
//    override suspend fun requestChatbot(
//        requestType: String,
//        word: String
//    ): Flow<> = dataSource.requestChatbot(requestType, word)
}