package com.example.avocado_android.ui.login

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityLoginBinding
import com.example.avocado_android.ui.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private var loginRoute = ""
    override fun setLayout() {
        setButton()
    }

    private fun setButton() {
        binding.loginGoHomeBtn.setOnClickListener {
            startNextActivity(MainActivity::class.java)
        }
        binding.loginKakaoLoginBtn.setOnClickListener {
            loginRoute = "kakao"
            val intent = Intent(this, RedirectionActivity::class.java).apply {
                putExtra("login", loginRoute)
            }
            startActivity(intent)
        }
        binding.loginNaverLoginBtn.setOnClickListener {
            loginRoute = "naver"
            val intent = Intent(this, RedirectionActivity::class.java).apply {
                putExtra("login", loginRoute)
            }
            startActivity(intent)
        }
    }
}