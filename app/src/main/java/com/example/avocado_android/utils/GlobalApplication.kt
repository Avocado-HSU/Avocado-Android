package com.example.avocado_android.utils

import android.app.Application
import com.example.avocado_android.utils.token.TokenManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class GlobalApplication: Application() {
    @Inject
    lateinit var tokenManager: TokenManager
}