package com.example.avocado_android.domain.model.response.library

import com.example.avocado_android.utils.enunm.ResponseType

data class UpdateLibraryResponseDto (
    val responseType: ResponseType,
    val libraryId: Int
)