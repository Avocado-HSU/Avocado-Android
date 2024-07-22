package com.example.avocado_android.data.source.chatbot

import android.util.Log
import com.example.avocado_android.data.remote.ChatBotApi
import javax.inject.Inject

class ChatBotDataSource @Inject constructor(
    private val chatBotApi: ChatBotApi
) {
//    fun requestChatbot(requestType: String, word: String) : Flow<> = flow {
//        val result = chatBotApi.requestChatbot(requestType, word)
//        emit(result)
//    }.catch {
//        Log.e("Post Call By sendPhoneNumbers Failure", it.message.toString())
//    }
}