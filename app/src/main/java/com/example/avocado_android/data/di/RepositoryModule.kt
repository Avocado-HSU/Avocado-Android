package com.example.avocado_android.data.di

import com.example.avocado_android.data.source.chatbot.ChatBotDataSource
import com.example.avocado_android.data.source.chatbot.ChatBotRepositoryImpl
import com.example.avocado_android.data.source.library.LibraryPageDataSource
import com.example.avocado_android.data.source.library.LibraryPageRepositoryImpl
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import com.example.avocado_android.domain.repository.library.LibraryPageRepository
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

    @Singleton
    @Provides
    fun provideLibraryPageRepository(libraryPageDataSource: LibraryPageDataSource) : LibraryPageRepository =
        LibraryPageRepositoryImpl(libraryPageDataSource)

    //fun provideMainPageRepository

    //fun provideMemberControlRepository

    //fun provideWordPageRepository

}