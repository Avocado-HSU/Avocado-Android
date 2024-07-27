package com.example.avocado_android.ui.ChatBot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avocado_android.R
import com.example.avocado_android.domain.model.local.chatbot.ChatItem
import com.example.avocado_android.domain.model.response.chatbot.ChatBotResponseDto
import com.example.avocado_android.domain.model.response.chatbot.WordSimilarDto
import com.example.avocado_android.domain.model.response.search.WordEtymologyDto
import com.example.avocado_android.domain.model.response.search.WordMeanDto
import com.example.avocado_android.domain.model.response.search.WordTipsDto
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatBotRepository: ChatBotRepository
) : ViewModel() {

    // 유사 단어
    private val _wordSimilarDto = MutableStateFlow(WordSimilarDto())
    val wordSimilarDto : StateFlow<WordSimilarDto> get() = _wordSimilarDto

    // 단어 뜻
    private val _wordMeanDto = MutableStateFlow(WordMeanDto())
    val wordMeanDto : StateFlow<WordMeanDto> get() = _wordMeanDto

    // 어원 분류
    private val _wordEtymologyDto = MutableStateFlow(WordEtymologyDto())
    val wordEtymologyDto : StateFlow<WordEtymologyDto> get() = _wordEtymologyDto

    fun getWordSimilar(requestType: String, word: String) {
        try {
            viewModelScope.launch {
                chatBotRepository.getWordSimilar(requestType, word).collect {
                    _wordSimilarDto.value = it
                    Log.d("ChatViewModel getWordSimilar", "getWordSimilar: $it")
                }
            }
        } catch (e: Exception) {
            Log.e("ChatViewModel getWordSimilar Error", e.message.toString())
        }
    }

    fun getWordMean(requestType: String, word: String) {
        try {
            viewModelScope.launch {
                chatBotRepository.getWordMean(requestType, word).collect {
                    _wordMeanDto.value = it
                    Log.d("ChatViewModel getWordMean", "getWordMean: $it")
                }
            }
        } catch (e: Exception) {
            Log.e("ChatViewModel getWordMean Error", e.message.toString())
        }
    }

    fun getWordEtymology(requestType: String, word: String) {
        try {
            viewModelScope.launch {
                chatBotRepository.getWordEtymology(requestType, word).collect {
                    _wordEtymologyDto.value = it
                    Log.d("ChatViewModel getWordEtymology", "getWordEtymology: $it")
                }
            }
        } catch (e: Exception) {
            Log.e("ChatViewModel getWordEtymology Error", e.message.toString())
        }
    }

    fun reset() {
        _wordSimilarDto.value = WordSimilarDto()
        _wordMeanDto.value = WordMeanDto()
        _wordEtymologyDto.value = WordEtymologyDto()
    }
}