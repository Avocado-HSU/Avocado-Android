package com.example.avocado_android.domain.model.response.search

data class WordEtymologyDto(
    val etymology: String? = null,
    val root: String? = null,
    val prefix: String? = null,
    val suffix: String? = null,
    val etymologyDescription: String? = null,
    val rootDescription: String? = null,
    val prefixDescription: String? = null,
    val suffixDescription: String? = null,
)
