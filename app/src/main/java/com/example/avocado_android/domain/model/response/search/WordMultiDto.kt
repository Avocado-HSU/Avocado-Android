package com.example.avocado_android.domain.model.response.search

data class WordMultiDto(
    val wordTipsDto: WordTipsDto? = null,
    val wordEtymologyDto: WordEtymologyDto? = null,
    val wordMeanDto: WordMeanDto? = null
)
