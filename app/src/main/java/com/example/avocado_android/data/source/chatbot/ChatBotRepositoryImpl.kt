package com.example.avocado_android.data.source.chatbot

import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.model.response.chatbot.WordSimilarDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatBotRepositoryImpl @Inject constructor(
    private val dataSource: ChatBotDataSource
) : ChatBotRepository {
    override suspend fun getWordTips(requestType: String, word: String): Flow<WordTipsDto> = dataSource.getWordTips(requestType, word)
    override suspend fun getWordSimilar(requestType: String, word: String): Flow<WordSimilarDto> = dataSource.getWordSimilar(requestType, word)
    override suspend fun getWordMean(requestType: String, word: String): Flow<WordEtymologyDto>  = dataSource.getWordMean(requestType, word)
    override suspend fun getWordEtymology(requestType: String, word: String): Flow<ChatBotResponseDto>  = dataSource.getWordEtymology(requestType, word)
}