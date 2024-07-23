package com.example.avocado_android.ui.ChatBot

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.avocado_android.R
import com.example.avocado_android.base.BaseAdapter
import com.example.avocado_android.base.BaseDiffCallback
import com.example.avocado_android.databinding.ItemChatbotBotMsgBinding
import com.example.avocado_android.databinding.ItemChatbotSelectionImgBinding
import com.example.avocado_android.databinding.ItemChatbotUserMsgBinding
import com.example.avocado_android.domain.model.local.chatbot.ChatItem

class ChatBotAdapter() : ListAdapter<ChatItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    inner class BotViewHolder(private val binding: ItemChatbotBotMsgBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindLeft(item: ChatItem.BotChatItem) {
            binding.itemChatbotBotMsgTv.text = item.msg
        }
    }

    inner class UserViewHolder(private val binding: ItemChatbotUserMsgBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindRight(item: ChatItem.UserChatItem) {
            binding.itemChatbotUserMsgTv.text = item.msg
        }
    }

    inner class ImageSelectionViewHolder(private val binding: ItemChatbotSelectionImgBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatItem.ChatCardItem) {
            binding.itemChatbotCardIv.setImageResource(item.imageUri)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChatItem.BotChatItem -> BOT
            is ChatItem.UserChatItem -> USER
            is ChatItem.ChatCardItem -> IMG
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return when (viewType) {
            BOT -> {
                val binding = ItemChatbotBotMsgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BotViewHolder(binding)
            }
            USER -> {
                val binding = ItemChatbotUserMsgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserViewHolder(binding)
            }
            IMG -> {
                val binding = ItemChatbotSelectionImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ImageSelectionViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ChatItem.BotChatItem -> {
                (holder as BotViewHolder).bindLeft(item)
            }
            is ChatItem.UserChatItem -> {
                (holder as UserViewHolder).bindRight(item)
            }
            is ChatItem.ChatCardItem -> {
                (holder as ImageSelectionViewHolder).bind(item)
            }
        }
    }

    companion object {
        private const val BOT = 1
        private const val USER = 2
        private const val IMG = 3

        private val diffUtilCallback = object : DiffUtil.ItemCallback<ChatItem>() {
            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem) = oldItem == newItem
        }
    }
}
