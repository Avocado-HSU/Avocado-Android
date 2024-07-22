package com.example.avocado_android.utils.token

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AccessTokenInterceptor  @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessToken()
        }
        val request = chain.request().newBuilder()
        request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token") // 헤더 값 "Bearer {token}" 형식으로 설정
        return chain.proceed(request.build()) // 요청 헤더에 엑세스 토큰 추가
    }
}