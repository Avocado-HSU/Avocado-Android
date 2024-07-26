package com.example.avocado_android.domain.model.local.chatbot

import com.example.avocado_android.utils.enunm.ChatBotResponseType

sealed class ChatItem {
    data class UserChatItem(val msg: String) : ChatItem()
    data class BotChatItem(val msg: String) : ChatItem()
    data class ChatCardListItem(val cards: List<ChatCardItem>) : ChatItem()
    data class ChatCardItem(val chatBotResponseType: ChatBotResponseType?, val imageUri: Int, val text: String) : ChatItem()
}