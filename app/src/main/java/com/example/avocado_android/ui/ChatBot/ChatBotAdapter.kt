package com.example.avocado_android.ui.ChatBot

import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
import com.example.avocado_android.databinding.ItemChatbotTipsBinding
import com.example.avocado_android.databinding.ItemChatbotUserMsgBinding
import com.example.avocado_android.domain.model.local.chatbot.ChatItem

class ChatBotAdapter(private val onCardClick: (ChatItem.ChatCardItem) -> Unit) : ListAdapter<ChatItem, RecyclerView.ViewHolder>(diffUtilCallback) {

    inner class BotViewHolder(private val binding: ItemChatbotBotMsgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindLeft(item: ChatItem.BotChatItem) {
            binding.itemChatbotBotMsgTv.text = item.msg
            binding.executePendingBindings()
        }
    }

    inner class UserViewHolder(private val binding: ItemChatbotUserMsgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindRight(item: ChatItem.UserChatItem) {
            binding.itemChatbotUserMsgTv.text = item.msg
            binding.executePendingBindings()
        }
    }

    inner class ImageSelectionViewHolder(private val binding: ItemChatbotSelectionImgBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem.ChatCardListItem) {
            // 아이템 뷰를 카드로 확장하여 카드 개수에 맞게 설정
            val cardViews = listOf(
                binding.itemChatbotCardView1,
                binding.itemChatbotCardView2,
                binding.itemChatbotCardView3,
                binding.itemChatbotCardView4
            )

            val imageViews = listOf(
                binding.itemChatbotCardIv1,
                binding.itemChatbotCardIv2,
                binding.itemChatbotCardIv3,
                binding.itemChatbotCardIv4
            )

            val textViews = listOf(
                binding.itemChatbotCardTv1,
                binding.itemChatbotCardTv2,
                binding.itemChatbotCardTv3,
                binding.itemChatbotCardTv4
            )

            for (i in item.cards.indices) {
                if (i < cardViews.size) {
                    val card = item.cards[i]
                    cardViews[i].visibility = View.VISIBLE
                    imageViews[i].setImageResource(card.imageUri)
                    textViews[i].text = card.text

                    cardViews[i].setOnClickListener {
                        onCardClick(card)
                    }
                }
            }

            // 카드가 부족한 경우 숨기기
            for (i in item.cards.size until cardViews.size) {
                cardViews[i].visibility = View.GONE
            }

            binding.executePendingBindings()
        }
    }

    inner class TipsViewHolder(private val binding: ItemChatbotTipsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatItem.ChatTipsListItem) {

            val cardViews = listOf(
                binding.itemChatbotCardView1,
                binding.itemChatbotCardView2,
                binding.itemChatbotCardView3,
                binding.itemChatbotCardView4
            )

            val imageViews = listOf(
                binding.itemChatbotCardIv1,
                binding.itemChatbotCardIv2,
                binding.itemChatbotCardIv3,
                binding.itemChatbotCardIv4
            )

            val titles = listOf(
                binding.itemChatbotCardTitleTv1,
                binding.itemChatbotCardTitleTv2,
                binding.itemChatbotCardTitleTv3,
                binding.itemChatbotCardTitleTv4
            )

            val description = listOf(
                binding.itemChatbotCardTv1,
                binding.itemChatbotCardTv2,
                binding.itemChatbotCardTv3,
                binding.itemChatbotCardTv4
            )

            for (i in item.cards.indices) {
                if (i < cardViews.size) {
                    val card = item.cards[i]
                    cardViews[i].visibility = View.VISIBLE
                    imageViews[i].setImageResource(card.imageUri)
                    titles[i].text = card.title
                    description[i].text = card.description
                }
            }

            // 카드가 부족한 경우 숨기기
            for (i in item.cards.size until cardViews.size) {
                cardViews[i].visibility = View.GONE
            }

            binding.executePendingBindings()
        }
    }


    // 어댑터 초기화
    fun reset() {
        submitList(emptyList())
    }

    // 여러 항목 추가
    fun addMultipleItems(items: List<ChatItem>) {
        val currentList = currentList.toMutableList()
        currentList.addAll(items)
        submitList(currentList)
    }

    // 챗봇의 말풍선 추가
    fun addBotChatItem(item: ChatItem.BotChatItem) {
        val currentList = currentList.toMutableList()
        currentList.add(item)
        submitList(currentList)
    }

    // 사용자 말풍선 추가
    fun addUserChatItem(item: ChatItem.UserChatItem) {
        val currentList = currentList.toMutableList()
        currentList.add(item)
        submitList(currentList)
    }

    // 질문 이미지 선택지 추가
    fun addChatCardListItem(items: ChatItem.ChatCardListItem) {
        val currentList = currentList.toMutableList()
        currentList.add(items)
        submitList(currentList)
    }

    // 팁 설명 이미지 추가
    fun addChatTipCardListItem(items: ChatItem.ChatTipsListItem) {
        val currentList = currentList.toMutableList()
        currentList.add(items)
        submitList(currentList)
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ChatItem.BotChatItem -> BOT
            is ChatItem.UserChatItem -> USER
            is ChatItem.ChatCardListItem -> IMG
            is ChatItem.ChatTipsListItem -> TIPS
            else -> throw IllegalArgumentException("Invalid getItemViewType")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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
            TIPS -> {
                val binding = ItemChatbotTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TipsViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid onCreateViewHolder")
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
            is ChatItem.ChatCardListItem -> {
                (holder as ImageSelectionViewHolder).bind(item)
            }
            is ChatItem.ChatTipsListItem -> {
                (holder as TipsViewHolder).bind(item)
            }
            else -> throw IllegalArgumentException("Invalid onBindViewHolder")
        }
    }

    companion object {
        private const val BOT = 0
        private const val USER = 1
        private const val IMG = 2
        private const val TIPS = 3

        private val diffUtilCallback = object : DiffUtil.ItemCallback<ChatItem>() {
            override fun areItemsTheSame(oldItem: ChatItem, newItem: ChatItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: ChatItem, newItem: ChatItem) = oldItem == newItem
        }
    }
}
