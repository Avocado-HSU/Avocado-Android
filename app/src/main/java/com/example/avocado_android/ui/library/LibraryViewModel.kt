package com.example.avocado_android.ui.library

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.domain.model.response.library.LibraryPageResponseDto
import com.example.avocado_android.domain.repository.librarypage.LibraryPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val libraryPageRepository: LibraryPageRepository
) : ViewModel(){
    private val _wordCardData = MutableStateFlow(LibraryPageResponseDto())
    val wordCardData : StateFlow<LibraryPageResponseDto> = _wordCardData

    fun getLibraryData(id: Long) {
        viewModelScope.launch {
            try {
                libraryPageRepository.getLibraryPage(id).collect {
                    _wordCardData.value = it
                }
            } catch (e: Exception) {
                Log.e("Get MemberData is Error", e.message.toString())
            }
        }
    }


}