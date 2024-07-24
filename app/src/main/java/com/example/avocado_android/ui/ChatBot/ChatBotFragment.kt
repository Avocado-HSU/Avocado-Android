package com.example.avocado_android.ui.ChatBot

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChatBotFragment : BaseFragment<FragmentChatbotBinding>(R.layout.fragment_chatbot) {

    private val viewModel by activityViewModels<ChatViewModel>()
    private lateinit var adapter: ChatBotAdapter

    override fun setLayout() {
        observeViewModel()
    }

    fun toast() {
        Toast.makeText(requireContext(), "ㅎㅎㅎ", Toast.LENGTH_SHORT).show()
    }

    private fun observeViewModel() {
        adapter = ChatBotAdapter()
        binding.chatbotChatRv.adapter = adapter

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    // 챗봇
                    viewModel.botMsgList.collect { botMsgList ->
                        val currentList = adapter.currentList.toMutableList()  // 기존 데이터를 가져옴
                        currentList.addAll(botMsgList) // 새로운 데이터를 기존 데이터에 추가
                        adapter.submitList(currentList) // 변경된 리스트를 Adapter에 전달
                    }
                }

//                launch {
//                    // 이미지
//                    viewModel.imageCardList.collect() { imageCardList ->
//                        adapter.submitList(imageCardList)
//                    }
                }
        }

        // 사용자
        binding.chatbotChatEt.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        viewModel.userMsgList.collect { userMsgList ->
                            val currentList = adapter.currentList.toMutableList()
                            currentList.addAll(userMsgList)
                            adapter.submitList(currentList)

                            // 키보드 내리고 내용 삭제
                            hideKeyboard(binding.chatbotChatEt, requireContext())
                            binding.chatbotChatEt.text = null
                        }
                    }
                }
                true
            } else {
                false
            }
        }
    }

    // 화면 터치 시 키보드 내리기
    private fun hideKeyboard(view: View, context: Context) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}