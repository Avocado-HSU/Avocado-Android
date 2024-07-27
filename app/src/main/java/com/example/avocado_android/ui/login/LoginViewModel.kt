package com.example.avocado_android.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.domain.model.local.home.MemberDto

import com.example.avocado_android.domain.repository.member.MemberControlRepository
import com.example.avocado_android.utils.token.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val memberControlRepository: MemberControlRepository,
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _member = MutableStateFlow(MemberDto())
    val memberData: StateFlow<MemberDto> = _member

    fun getMemberData() {
        viewModelScope.launch {
            try {
                memberControlRepository.getCurrentUser().collect {
                    _member.value = it
                }
            } catch (e: Exception) {
                Log.e("Get MemberData is Error", e.message.toString())
            }
        }
    }
    fun getToken(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            tokenManager.getAccessToken().collect { token ->
                onResult(token)
            }
        }
    }
    fun getCookie(onResult: (String?) -> Unit) {
        viewModelScope.launch {
            tokenManager.getCookie().collect { token ->
                onResult(token)
            }
        }
    }
}