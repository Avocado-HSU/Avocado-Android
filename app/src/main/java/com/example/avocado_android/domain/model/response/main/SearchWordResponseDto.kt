package com.example.avocado_android.domain.model.response.main

data class SearchWordResponseDto (
    val isSuccess : Boolean = false,
    val isLibraryRegistered: Boolean = false,
    val libraryId: Int = 0,
    val characterImgUrl: String = ""
)