package com.example.avocado_android.domain.model.response.library

data class LibraryWordDto(
    val libraryId: Int,
    val english: String,
    val korean: String,
    val etymologyList: List<String>
)
