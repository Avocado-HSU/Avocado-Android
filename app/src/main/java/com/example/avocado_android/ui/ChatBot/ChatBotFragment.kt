package com.example.avocado_android.ui.ChatBot

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseFragment
import com.example.avocado_android.databinding.FragmentChatbotBinding
import com.example.avocado_android.ui.vocalist.VocaListViewModel

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

//        viewModel.userMsgList.observe(viewLifecycleOwner) { userMsgList ->
//            adapter.submitList(userMsgList)
//        }

        viewModel.botMsgList.observe(viewLifecycleOwner) { botMsgList ->
            Log.d("로그", "DDdd")
            adapter.submitList(botMsgList)
        }

//        viewModel.imageCardList.observe(viewLifecycleOwner) { imageCardList ->
//            adapter.submitList(imageCardList)
//        }
    }
}