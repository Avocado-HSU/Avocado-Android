package com.example.avocado_android.ui.ChatBot

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemChatbotBotMsgBinding
import com.example.avocado_android.databinding.ItemChatbotSelectionImgBinding
import com.example.avocado_android.databinding.ItemChatbotUserMsgBinding
import com.example.avocado_android.domain.model.chatbot.ChatItem

class ChatBotAdapter() : BaseAdapter<ChatItem.BotChatItem, ItemChatbotBotMsgBinding>(
    diffCallback = BaseDiffCallback(
        areItemsTheSame = { oldItem, newItem -> oldItem == newItem },
        areContentsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {
    override var layoutId: Int = R.layout.item_chatbot_bot_msg

//    override fun getItemViewType(position: Int): Int {
//        return when (val item = getItem(position)) {
//            is ChatItem.UserChatItem ->  {
//                layoutId = R.layout.item_chatbot_user_msg
//                R.layout.item_chatbot_user_msg
//            }
//            is ChatItem.BotChatItem -> {
//                layoutId = R.layout.item_chatbot_bot_msg
//                R.layout.item_chatbot_bot_msg
//            }
//            is ChatItem.ChatCardItem -> {
//                layoutId = R.layout.item_chatbot_selection_img
//                R.layout.item_chatbot_selection_img
//            }
//        }
//    }

    override fun bind(binding: ItemChatbotBotMsgBinding, item: ChatItem.BotChatItem) {
        when (item) {
//            is ChatItem.UserChatItem -> {
//                val userBinding = binding as ItemChatbotUserMsgBinding
//                userBinding.itemChatbotUserMsgTv.text = item.msg
//            }
            is ChatItem.BotChatItem -> {
                binding.itemChatbotBotMsgTv.text = item.msg
                Log.d("로그", "${binding.itemChatbotBotMsgTv.text}")
            }
//            is ChatItem.ChatCardItem -> {
//                val imageBinding = binding as ItemChatbotSelectionImgBinding
//                // 이미지 로드 처리 예시 (Glide 라이브러리 사용)
//                Glide.with(binding.root.context)
//                    .load(item.imageUri)
//                    .into(imageBinding.itemChatbotCardIv)
//            }
        }
    }
}
