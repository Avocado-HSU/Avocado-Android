package com.example.avocado_android.data.source.member

import com.example.avocado_android.domain.model.local.home.MemberDto
import com.example.avocado_android.domain.repository.member.MemberControlRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val dataSource: MemberDataSource
) : MemberControlRepository{
    override suspend fun getCurrentUser(): Flow<MemberDto> = dataSource.getCurrentMember()
}