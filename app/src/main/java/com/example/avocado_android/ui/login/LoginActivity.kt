package com.example.avocado_android.ui.login

import android.app.Activity
import android.widget.Toast
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseActivity
import com.example.avocado_android.databinding.ActivityLoginBinding
import com.example.avocado_android.ui.MainActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun setLayout() {
        setButton()
    }

    private fun setButton() {
        binding.loginGoHomeBtn.setOnClickListener {
            startNextActivity(MainActivity::class.java)
        }
    }

}