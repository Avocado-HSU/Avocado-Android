package com.example.avocado_android.domain.model.response.search


data class SearchWordResponseDto(
    val isSuccess: Boolean? = null,
    val isLibraryRegistered: Boolean? = null,
    val libraryId: Long? = null,
    val korean: String? = null,
    val characterImgUrl: String? = null,
    val contents: WordMultiDto? = null,
)