package com.example.avocado_android.utils.token


import com.example.avocado_android.utils.token.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class CookieInterceptor(
    private val tokenManager: TokenManager
) : Interceptor {
    companion object {
        const val COOKIE = "Cookie"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val cookie = runBlocking {
            tokenManager.getCookie()
        }
        val request = chain.request().newBuilder()
            .addHeader(COOKIE, cookie.toString()) // 쿠키 값 추가
            .build()
        return chain.proceed(request)
    }
}
