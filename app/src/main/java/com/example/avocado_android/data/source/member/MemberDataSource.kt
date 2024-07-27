package com.example.avocado_android.data.source.member

import android.util.Log
import com.example.avocado_android.data.remote.MemberControlApi
import com.example.avocado_android.domain.model.local.home.MemberDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberDataSource @Inject constructor(
    private val memberControlApi: MemberControlApi
){
    fun getCurrentMember(): Flow<MemberDto> = flow {
        val result = memberControlApi.getCurrentUser()
        emit(result)
    }.catch {
        Log.e("memberDto response is Failure", it.message.toString())
    }
}