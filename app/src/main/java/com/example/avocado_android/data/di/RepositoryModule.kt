package com.example.avocado_android.data.di

import com.example.avocado_android.data.source.chatbot.ChatBotDataSource
import com.example.avocado_android.data.source.chatbot.ChatBotRepositoryImpl
import com.example.avocado_android.domain.repository.ChatBotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideChatBotRepository(chatBotDataSource: ChatBotDataSource): ChatBotRepository =
        ChatBotRepositoryImpl(chatBotDataSource)

    // 이후 리포지토리 추가하고 추가하면 됨
}