package com.example.avocado_android.domain.model.response

data class Member(
    val createdAt: String,
    val updatedAt: String,
    val id: Long,
    val username: String,
    val name: String,
    val role: String,
    val nickName: String,
    val email: String,
    val profileUrl: String,
    val point: Long,
    val oauthId: String
)