package com.example.avocado_android.utils.token


import android.util.Log
import com.example.avocado_android.utils.token.TokenManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CookieInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    companion object {
        const val COOKIE = "Cookie"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getCookie().firstOrNull() // Flow에서 첫 번째 값을 가져옴
        }
        val request = chain.request().newBuilder()
            .apply {
                token?.let {
                    addHeader(CookieInterceptor.COOKIE, it)
                    Log.d("헤더","${AccessTokenInterceptor.TOKEN_TYPE} $it")
                }
            }
            .build()
        return chain.proceed(request)
    }
}
