package com.example.avocado_android.ui.login

import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityRedirectionBinding
import com.example.avocado_android.ui.MainActivity

class RedirectionActivity : BaseActivity<ActivityRedirectionBinding>(R.layout.activity_redirection) {
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

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                    Log.d("RedirectionActivity", "Loading URL: $url") // 로딩되는 URL 확인
                    if (url.startsWith("http://avocado-hsu.kro.kr/")) {
                        handleCookies(url)
                        return true
                    }
                    return false
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (url != null && url.startsWith("http://avocado-hsu.kro.kr/")) {
                        handleCookies(url)
                    }
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
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
        if (cookies != null) {
            val cookieArray = cookies.split(";")
            for (cookie in cookieArray) {
                if (cookie.trim().startsWith("Authorization=")) {
                    val token = cookie.substringAfter("Authorization=").trim()
                    handleToken(token)
                    break
                }
            }
        }
    }

    private fun handleToken(token: String?) {
        if (token != null) {
            // 토큰 로그 출력
            Log.d("RedirectionActivity", "Received token: $token")
            // 다음 액티비티로 이동
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("token", token)
            startActivity(intent)
            finish()
        } else {
            // 토큰이 없을 경우 처리
            Log.d("RedirectionActivity", "No token received")
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
