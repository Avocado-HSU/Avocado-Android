package com.example.avocado_android.domain.model.response.main

data class SearchWordResponseDto (
    val isSuccess : Boolean,
    val isLibraryRegistered: Boolean,
    val libraryId: Int,
    val characterImgUrl: String
)