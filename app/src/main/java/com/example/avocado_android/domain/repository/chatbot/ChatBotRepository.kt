package com.example.avocado_android.domain.repository.chatbot

import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.model.response.chatbot.WordSimilarDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
import kotlinx.coroutines.flow.Flow

interface ChatBotRepository {
    suspend fun getWordTips(requestType: String, word: String): Flow<WordTipsDto>
    suspend fun getWordSimilar(requestType: String, word: String): Flow<WordSimilarDto>
    suspend fun getWordMean(requestType: String, word: String): Flow<WordEtymologyDto>
    suspend fun getWordEtymology(requestType: String, word: String): Flow<ChatBotResponseDto>
}