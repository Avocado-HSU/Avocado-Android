package com.example.avocado_android.domain.model.response.library

data class LibraryPageResponseDto (
    val characterImgUrl: String = "",
    val libraryWordDtoList: List<LibraryWordDto> = listOf()
)