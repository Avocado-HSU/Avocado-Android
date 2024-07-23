package com.example.avocado_android.ui.login

import android.content.Intent
import android.net.http.SslError
import android.util.Log
import android.webkit.CookieManager
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityRedirectionBinding
import com.example.avocado_android.ui.MainActivity
import com.example.avocado_android.utils.token.TokenManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RedirectionActivity :
    BaseActivity<ActivityRedirectionBinding>(R.layout.activity_redirection) {
    private val redirectionUrlForKakao = "http://avocado-hsu.kro.kr/oauth2/authorization/kakao"
    private val redirectionUrlForNaver = "http://avocado-hsu.kro.kr/oauth2/authorization/naver"

    override fun setLayout() {
        setWebView()
    }

    private fun setWebView() {
        val loginType = intent.getStringExtra("login")!!
        with(binding.activityRedirectionWebViewWb) {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            CookieManager.getInstance().setAcceptCookie(true)
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val url = request?.url.toString()
                    Log.d("리다이렉트 로딩", "Loading URL: $url")
                    if (url.startsWith("http://avocado-hsu.kro.kr/login/oauth2/code/kakao") || url.startsWith("http://avocado-hsu.kro.kr/login/oauth2/code/naver")) {
                        handleCookies(url)
                    }

                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    if (url != null && (url.startsWith("http://avocado-hsu.kro.kr/") || url.startsWith("http://avocado-hsu.kro.kr/login"))) {
                        finish()
                    }
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    Log.d("리다이렉트 ssl에러", "SSL Error: $error")
                    handler?.proceed()
                }
            }

            when (loginType) {
                "kakao" -> loadUrl(redirectionUrlForKakao)
                "naver" -> loadUrl(redirectionUrlForNaver)
            }
        }
    }

    private fun handleCookies(url: String) {
        val cookieManager = CookieManager.getInstance()
        val cookies = cookieManager.getCookie(url)
        Log.d("리다이렉트 쿠키", "Cookies: $cookies")
        if (cookies != null) {
            val tokenManager = TokenManager(this)
            val cookieArray = cookies.split(";")
            var authorizationToken: String? = null
            var sessionId: String? = null

            for (cookie in cookieArray) {
                Log.d("리다이렉트 쿠키확인", "Cookie: $cookie")
                when {
                    cookie.trim().startsWith("Authorization=") -> {
                        authorizationToken = cookie.split("=")[1]
                    }
                    cookie.trim().startsWith("JSESSIONID=") -> {
                        sessionId = cookie.trim()
                    }
                }
            }

            // 저장
            authorizationToken?.let { handleToken(it) }
            sessionId?.let { tokenManager.saveCookie(it) }
        }
    }

    private fun handleToken(token: String?) {
        if (token != null) {
            // 토큰 로그 출력
            Log.d("리다이렉트 토큰", "Received token: $token")

            // 토큰 저장
            val tokenManager = TokenManager(this)
            tokenManager.saveToken(token)

            // 다음 액티비티로 이동
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        } else {
            // 토큰이 없을 경우 처리
            Log.d("리다이렉트 토큰없어", "No token received")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 뒤로 가기 버튼을 누르면 첫 화면으로 이동
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
