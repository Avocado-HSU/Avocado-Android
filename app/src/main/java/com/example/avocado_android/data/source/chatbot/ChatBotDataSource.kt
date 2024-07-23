package com.example.avocado_android.data.source.chatbot

import android.util.Log
import com.example.avocado_android.data.remote.ChatBotApi
import com.example.avocado_android.domain.model.response.ChatBotResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatBotDataSource @Inject constructor(
    private val chatBotApi: ChatBotApi
) {
    fun requestChatbot(requestType: String, word: String) : Flow<ChatBotResponseDto> = flow {
        val result = chatBotApi.requestChatbot(requestType, word)
        emit(result)
    }.catch {
        Log.e("Post requestChatbot By ChatBotResponseDto Failure", it.message.toString())
    }
}