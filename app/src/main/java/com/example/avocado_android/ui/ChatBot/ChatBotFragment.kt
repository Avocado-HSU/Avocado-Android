package com.example.avocado_android.ui.ChatBot

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentChatbotBinding
import com.example.avocado_android.domain.model.local.chatbot.ChatItem
import com.example.avocado_android.ui.search.SearchViewModel
import com.example.avocado_android.utils.enunm.ChatBotResponseType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatBotFragment : BaseFragment<FragmentChatbotBinding>(R.layout.fragment_chatbot) {

    private val chatViewModel : ChatViewModel by activityViewModels()
    private lateinit var adapter: ChatBotAdapter

    private var query: String = ""
    private var selectedResponseType: ChatBotResponseType? = null

    override fun setLayout() {
        setAdapter()
        observeViewModel()
        setInitialMessage()
        sendQuestionToChatBot()
    }

    override fun onStop() {
        super.onStop()
        resetAll()
    }

    // 챗봇 화면 들어오면 뷰모델, 어댑터 reset
    private fun resetAll() {
        chatViewModel.reset()
        adapter.reset()
    }

    // 챗봇 질문 선택 이미지 추가
    private fun addChatCardItems() : ChatItem.ChatCardListItem {
        val initialChatCardItems = listOf(
            ChatItem.ChatCardItem(ChatBotResponseType.DEFINE, R.drawable.ic_chatbot_meaning_word,  getString(R.string.chatbot_meaning)),
            ChatItem.ChatCardItem(ChatBotResponseType.SIMILAR, R.drawable.ic_chatbot_similar_word, getString(R.string.chatbot_similar)),
            ChatItem.ChatCardItem(ChatBotResponseType.ETYMOLOGY, R.drawable.ic_chatbot_etymology, getString(R.string.chatbot_etymology)),
            ChatItem.ChatCardItem(ChatBotResponseType.TIP, R.drawable.ic_chatbot_tips, getString(R.string.chatbot_tips))
        )
        val chatCardListItem = ChatItem.ChatCardListItem(initialChatCardItems)
        return chatCardListItem
    }

    // 팁 이미지 선택시 나오는 이미지
    private fun addChatTipCardItems() : ChatItem.ChatTipsListItem {
        val initialChatTipItems = listOf(
            ChatItem.ChatTipItem(R.drawable.ic_chatbot_tips_1, getString(R.string.chatbot_tip_card_title1), getString(R.string.chatbot_tip_card_desc1)),
            ChatItem.ChatTipItem(R.drawable.ic_chatbot_tips_2, getString(R.string.chatbot_tip_card_title2), getString(R.string.chatbot_tip_card_desc2)),
            ChatItem.ChatTipItem(R.drawable.ic_chatbot_tips_3, getString(R.string.chatbot_tip_card_title3),getString(R.string.chatbot_tip_card_desc3)),
            ChatItem.ChatTipItem(R.drawable.ic_chatbot_tips_4, getString(R.string.chatbot_tip_card_title4), getString(R.string.chatbot_tip_card_desc4))
        )
        val chatTipsListItem = ChatItem.ChatTipsListItem(initialChatTipItems)
        return chatTipsListItem
    }

    private fun setInitialMessage() {
        val initialMessage = ChatItem.BotChatItem(msg = "안녕하세요!\n 영단어 공부 챗봇 '아보카'입니다!\n 무엇이 궁금하신가요?")
        val initialChatCardItems = addChatCardItems()
        adapter.submitList(listOf(initialMessage) + initialChatCardItems)
    }

    private fun setAdapter() {
        adapter = ChatBotAdapter { card ->
            handleCardClick(card)
        }
        binding.chatbotChatRv.adapter = adapter
    }

    private fun observeViewModel() {

        // 유사 단어
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.wordSimilarDto.collectLatest { wordSimilarDto ->
                    if(wordSimilarDto.greetingMsg != null) {
                        val currentList = adapter.currentList.toMutableList()
                        val newItems = mutableListOf<ChatItem.BotChatItem>()

                        // 문자열 파싱하여 하나의 문자열로 만듦
                        val greetingMessage = wordSimilarDto.greetingMsg.trim('.')
                        val contentsString = wordSimilarDto.contents?.joinToString(separator = "\n") { it.trimEnd('.') } ?: ""
                        val queryString = "$query 검색 결과입니다."
                        val combinedString = "$queryString\n\n$greetingMessage\n$contentsString"
                        newItems.apply {
                            add(ChatItem.BotChatItem(combinedString))
                        }
                        currentList.addAll(newItems)
                        currentList.add(ChatItem.BotChatItem(getString(R.string.chatbot_answer_last)))
                        currentList.add(addChatCardItems())
                        adapter.submitList(currentList)
                    }
                }
            }
        }

        // 단어적 의미
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.wordMeanDto.collectLatest { wordMeanDto ->
                    if (wordMeanDto.greetingMsg != null) {
                        val currentList = adapter.currentList.toMutableList()
                        val newItems = mutableListOf<ChatItem.BotChatItem>()

                        val queryString = "$query 검색 결과입니다."
                        val meaningsString = wordMeanDto.meanings?.entries?.joinToString(separator = "\n") {
                            "${it.key}: ${it.value}"
                        } ?: ""
                        val combinedString = "$queryString\n\n\n$meaningsString"
                        newItems.apply {
                            add(ChatItem.BotChatItem(combinedString))
                        }
                        currentList.addAll(newItems)
                        currentList.add(ChatItem.BotChatItem(getString(R.string.chatbot_answer_last)))
                        currentList.add(addChatCardItems())
                        adapter.submitList(currentList)
                    }
                }
            }
        }

        // 어원 분류 chatBotResponseDto
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                chatViewModel.wordEtymologyDto.collectLatest { wordEtymologyDto ->
                    if (wordEtymologyDto.etymology != null ||
                        wordEtymologyDto.root != null ||
                        wordEtymologyDto.prefix != null ||
                        wordEtymologyDto.suffix != null ||
                        wordEtymologyDto.etymologyDescription != null ||
                        wordEtymologyDto.rootDescription != null ||
                        wordEtymologyDto.prefixDescription != null ||
                        wordEtymologyDto.suffixDescription != null) {

                        val currentList = adapter.currentList.toMutableList()

                        val rootString = if (!wordEtymologyDto.root.isNullOrEmpty()) {
                            "어간 (Root): ${wordEtymologyDto.root ?: ""}\n${wordEtymologyDto.rootDescription ?: ""}".trim()
                        } else {
                            ""
                        }
                        val prefixString = if (!wordEtymologyDto.prefix.isNullOrEmpty() || !wordEtymologyDto.prefixDescription.isNullOrEmpty()) {
                            "접두사 (Prefix): ${wordEtymologyDto.prefix ?: ""}\n${wordEtymologyDto.prefixDescription ?: ""}".trim()
                        } else {
                            ""
                        }
                        val suffixString = if (!wordEtymologyDto.suffix.isNullOrEmpty() || !wordEtymologyDto.suffixDescription.isNullOrEmpty()) {
                            "접미사 (Suffix): ${wordEtymologyDto.suffix ?: ""}\n${wordEtymologyDto.suffixDescription ?: ""}".trim()
                        } else {
                            ""
                        }
                        val etymologyDescription = if (!wordEtymologyDto.etymologyDescription.isNullOrEmpty()) {
                            wordEtymologyDto.etymologyDescription
                        } else ""

                        val combinedString = listOf(
                            rootString,
                            prefixString,
                            suffixString,
                            etymologyDescription
                        ).filter { it.isNotEmpty() }.joinToString(separator = "\n\n")
                        val finalMessage = combinedString.ifEmpty { getString(R.string.chatbot_answer_null) }

                        currentList.add(ChatItem.BotChatItem(finalMessage))
                        currentList.add(ChatItem.BotChatItem(getString(R.string.chatbot_answer_last)))
                        currentList.add(addChatCardItems())
                        adapter.submitList(currentList)
                    }
                }
            }
        }

    }

    // 검색 선택 이미지 클릭 리스너
    private fun handleCardClick(card: ChatItem.ChatCardItem) {
        when (card.chatBotResponseType) {
            ChatBotResponseType.DEFINE -> {
                selectedResponseType = ChatBotResponseType.DEFINE
                val itemsToAdd = listOf(
                    ChatItem.UserChatItem(getString(R.string.chatbot_meaning)),
                    ChatItem.BotChatItem(getString(R.string.chatbot_which_word))
                )
                adapter.addMultipleItems(itemsToAdd)
            }
            ChatBotResponseType.SIMILAR -> {
                selectedResponseType = ChatBotResponseType.SIMILAR
                val itemsToAdd = listOf(
                    ChatItem.UserChatItem(getString(R.string.chatbot_similar)),
                    ChatItem.BotChatItem(getString(R.string.chatbot_which_word))
                )
                adapter.addMultipleItems(itemsToAdd)
            }
            ChatBotResponseType.ETYMOLOGY -> {
                selectedResponseType = ChatBotResponseType.ETYMOLOGY
                val itemsToAdd = listOf(
                    ChatItem.UserChatItem(getString(R.string.chatbot_etymology)),
                    ChatItem.BotChatItem(getString(R.string.chatbot_which_word))
                )
                adapter.addMultipleItems(itemsToAdd)
            }
            ChatBotResponseType.TIP -> {
                selectedResponseType = ChatBotResponseType.TIP
                val itemsToAdd = listOf(
                    ChatItem.UserChatItem(getString(R.string.chatbot_tips)),
                    ChatItem.BotChatItem(getString(R.string.chatbot_which_word))
                )
                adapter.addMultipleItems(itemsToAdd)
            }
            else -> true
        }
    }

    // 서버로 챗봇에 보낼 질문 전송
    private fun sendQuestionToChatBot() {
        binding.chatbotChatEt.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        query = binding.chatbotChatEt.text.toString()
                        hideKeyboard(binding.chatbotChatEt, requireContext())
                        binding.chatbotChatEt.text = null
                        when(selectedResponseType) {
                            ChatBotResponseType.DEFINE -> {
                                adapter.addUserChatItem(ChatItem.UserChatItem(query))
                                chatViewModel.getWordMean(selectedResponseType.toString(), query)
                            }
                            ChatBotResponseType.SIMILAR -> {
                                adapter.addUserChatItem(ChatItem.UserChatItem(query))
                                chatViewModel.getWordSimilar(selectedResponseType.toString(), query)
                            }
                            ChatBotResponseType.ETYMOLOGY -> {
                                adapter.addUserChatItem(ChatItem.UserChatItem(query))
                                chatViewModel.getWordEtymology(selectedResponseType.toString(), query)
                            }

                            // 서버 처리 X
                            ChatBotResponseType.TIP -> {
                                val userChatItem = ChatItem.UserChatItem(query)
                                val chatTipItems = addChatTipCardItems()
                                val botChatItem = ChatItem.BotChatItem(getString(R.string.chatbot_answer_last))
                                val chatCardItems = addChatCardItems()
                                val itemsToAdd = listOf(
                                    userChatItem,
                                    chatTipItems,
                                    botChatItem,
                                    chatCardItems
                                )
                                adapter.addMultipleItems(itemsToAdd)
                            }
                            else -> true
                        }
                    }
                }
            }
            true
        }
    }

    // 화면 터치 시 키보드 내리기
    private fun hideKeyboard(view: View, context: Context) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}