package com.example.avocado_android.data.di

import com.example.avocado_android.data.source.chatbot.ChatBotDataSource
import com.example.avocado_android.data.source.chatbot.ChatBotRepositoryImpl
import com.example.avocado_android.data.source.member.MemberDataSource
import com.example.avocado_android.data.source.member.MemberRepositoryImpl
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import com.example.avocado_android.domain.repository.member.MemberControlRepository
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

    //fun provideLibraryPageRepository

    //fun provideMainPageRepository

    @Singleton
    @Provides
    fun provideMemberControlRepository(memberDataSource: MemberDataSource): MemberControlRepository =
        MemberRepositoryImpl(memberDataSource)

    //fun provideWordPageRepository

}