package com.example.avocado_android.base

data class ApiResponse<T>(
    val code: Int,
    val body: T?
)