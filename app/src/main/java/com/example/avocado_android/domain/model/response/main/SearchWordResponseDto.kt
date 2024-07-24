package com.example.avocado_android.domain.model.response.main

import com.example.avocado_android.utils.enunm.SearchRequestType


data class SearchWordResponseDto(
    val isSuccess: Boolean? = null,
    val isLibraryRegistered: Boolean? = null,
    val libraryId: Long? = null,
    val characterImgUrl: String? = null,
    val contents: Map<SearchRequestType, String>? = null
)