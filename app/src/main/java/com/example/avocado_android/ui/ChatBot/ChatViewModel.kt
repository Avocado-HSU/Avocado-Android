package com.example.avocado_android.ui.ChatBot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avocado_android.domain.model.chatbot.ChatItem

class ChatViewModel: ViewModel() {
    private val _userMsgList = MutableLiveData<List<ChatItem.UserChatItem>>()
    val userMsgList : LiveData<List<ChatItem.UserChatItem>> get() = _userMsgList

    private val _botMsgList = MutableLiveData<List<ChatItem.BotChatItem>>()
    val botMsgList : LiveData<List<ChatItem.BotChatItem>> get() = _botMsgList

    private val _imageCardList = MutableLiveData<List<ChatItem.ChatCardItem>>()
    val imageCardList : LiveData<List<ChatItem.ChatCardItem>> get() = _imageCardList

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
            ChatItem.ChatCardItem("R.drawable.ic_chatbot_logo"),
            ChatItem.ChatCardItem("R.drawable.ic_chatbot_mike"),
            ChatItem.ChatCardItem("R.drawable.ic_chatbot_logo"),
            ChatItem.ChatCardItem("R.drawable.ic_chatbot_mike"),
            ChatItem.ChatCardItem("R.drawable.ic_chatbot_logo"),
        )
    }
}