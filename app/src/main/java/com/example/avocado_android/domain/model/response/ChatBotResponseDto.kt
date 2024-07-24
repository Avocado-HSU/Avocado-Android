package com.example.avocado_android.domain.model.response

data class ChatBotResponseDto (
    val isSuccess: Boolean = false,
    val content: String = "",
    val success: Boolean = false
)