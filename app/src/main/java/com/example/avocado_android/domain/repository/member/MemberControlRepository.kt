package com.example.avocado_android.domain.repository.member

import com.example.avocado_android.domain.model.home.MemberDto
import kotlinx.coroutines.flow.Flow

interface MemberControlRepository {
    suspend fun getCurrentUser(): Flow<MemberDto>
}