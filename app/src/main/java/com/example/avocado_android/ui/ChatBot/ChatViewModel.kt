package com.example.avocado_android.ui.ChatBot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avocado_android.R
import com.example.avocado_android.domain.model.local.chatbot.ChatItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel: ViewModel() {
    private val _userMsgList = MutableStateFlow<List<ChatItem.UserChatItem>>(emptyList())
    val userMsgList : StateFlow<List<ChatItem.UserChatItem>> get() = _userMsgList

    private val _botMsgList = MutableStateFlow<List<ChatItem.BotChatItem>>(emptyList())
    val botMsgList : StateFlow<List<ChatItem.BotChatItem>> get() = _botMsgList

    private val _imageCardList = MutableStateFlow<List<ChatItem.ChatCardItem>>(emptyList())
    val imageCardList : StateFlow<List<ChatItem.ChatCardItem>> get() = _imageCardList

    init {
        _userMsgList.value = listOf(
            ChatItem.UserChatItem(0, "hi"),
            ChatItem.UserChatItem(0, "I am user"),
        )

        _botMsgList.value = listOf(
            ChatItem.BotChatItem(0, "hi"),
            ChatItem.BotChatItem(0, "I am bot"),
        )

        _imageCardList.value = listOf(
            ChatItem.ChatCardItem(R.drawable.ic_chatbot_logo),
            ChatItem.ChatCardItem(R.drawable.ic_chatbot_mike),
            ChatItem.ChatCardItem(R.drawable.ic_chatbot_logo),
            ChatItem.ChatCardItem(R.drawable.ic_chatbot_mike),
            ChatItem.ChatCardItem(R.drawable.ic_chatbot_logo),
        )
    }
}