package com.example.avocado_android.data.remote

import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.model.response.chatbot.WordSimilarDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordMeanDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatBotApi {

    // 단어 외우기 팁
    @POST("/api/test/getWordTips/{requestType}/{word}")
    suspend fun getWordTips(
        @Path("requestType") requestType: String,
        @Path("word") word: String
    )  : WordTipsDto

    // 유사 단어
    @POST("/api/test/getWordSimilar/{requestType}/{word}")
    suspend fun getWordSimilar(
        @Path("requestType") requestType: String,
        @Path("word") word: String
    )  : WordSimilarDto

    // 단어 뜻
    @POST("/api/test/getWordMean/{requestType}/{word}")
    suspend fun getWordMean(
        @Path("requestType") requestType: String,
        @Path("word") word: String
    )  : WordMeanDto

    // 어원 분류
    @POST("/api/test/getWordEtymology/{requestType}/{word}")
    suspend fun getWordEtymology(
        @Path("requestType") requestType: String,
        @Path("word") word: String
    )  : WordEtymologyDto

}