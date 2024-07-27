package com.example.avocado_android.domain.model.response.search

data class WordMeanDto(
    val meanings: Map<String, String>? = null,
    val greetingMsg: String? = null
)