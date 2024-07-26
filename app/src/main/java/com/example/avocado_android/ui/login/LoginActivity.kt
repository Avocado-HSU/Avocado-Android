package com.example.avocado_android.ui.login

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityLoginBinding
import com.example.avocado_android.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val memberControlViewModel by viewModels<LoginViewModel>()
    private var loginRoute = ""
    override fun setLayout() {
        loginConfirm()
        setButton()
    }

    private fun loginConfirm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                memberControlViewModel.memberData.collectLatest { response ->
                    Log.d("response data", response.id.toString())
                    Log.d("response data", response.nickName.toString())
                    Log.d("response data", response.name.toString())
                    Log.d("response data", response.oauthId.toString())

                    memberControlViewModel.getToken { token ->
                        Log.d("response data", "Token: $token")
                        if (token != null) {
                            startNextActivity(MainActivity::class.java)
                            finish()
                        }
                    }

                }
            }
        }
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