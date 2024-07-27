package com.example.avocado_android.data.source.chatbot

import android.util.Log
import com.example.avocado_android.data.remote.ChatBotApi
import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.model.response.chatbot.WordSimilarDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordMeanDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatBotDataSource @Inject constructor(
    private val chatBotApi: ChatBotApi
) {
    fun getWordTips(requestType: String, word: String): Flow<WordTipsDto>  = flow {
        val result = chatBotApi.getWordTips(requestType, word)
        emit(result)
    }.catch {
        Log.e("Post getWordTips Failure", it.message.toString())
    }
    fun getWordSimilar(requestType: String, word: String): Flow<WordSimilarDto> = flow {
        val result = chatBotApi.getWordSimilar(requestType, word)
        emit(result)
    }.catch {
        Log.e("Post getWordSimilar Failure", it.message.toString())
    }
    fun getWordMean(requestType: String, word: String): Flow<WordMeanDto> = flow {
        val result = chatBotApi.getWordMean(requestType, word)
        emit(result)
    }.catch {
        Log.e("Post getWordMean Failure", it.message.toString())
    }
    fun getWordEtymology(requestType: String, word: String): Flow<WordEtymologyDto> = flow {
        val result = chatBotApi.getWordEtymology(requestType, word)
        emit(result)
    }.catch {
        Log.e("Post getWordEtymology Failure", it.message.toString())
    }
}