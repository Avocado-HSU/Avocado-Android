package com.example.avocado_android.domain.model.chatbot

sealed class ChatItem {
    data class UserChatItem(val id: Long, val msg: String) : ChatItem()
    data class BotChatItem(val id: Long, val msg: String) : ChatItem()
    data class ChatCardItem(val imageUri: String) : ChatItem()
}