package com.example.avocado_android.domain.model.response.library

data class LibraryWordDto(
    val libraryUpdatedTime : String = "",
    val libraryId: Int = 0,
    val english: String = "",
    val korean: String = "",
    val etymologyList: List<String> = listOf()
)
