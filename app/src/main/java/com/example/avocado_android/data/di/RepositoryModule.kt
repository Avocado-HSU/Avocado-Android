package com.example.avocado_android.data.di

import com.example.avocado_android.data.source.chatbot.ChatBotDataSource
import com.example.avocado_android.data.source.chatbot.ChatBotRepositoryImpl
import com.example.avocado_android.data.source.library.LibraryPageDataSource
import com.example.avocado_android.data.source.library.LibraryPageRepositoryImpl
import com.example.avocado_android.data.source.mainpage.MainPageDataSource
import com.example.avocado_android.data.source.mainpage.MainPageRepositoryImpl
import com.example.avocado_android.data.source.wordpage.WordPageDataSource
import com.example.avocado_android.data.source.wordpage.WordPageRepositoryImpl
import com.example.avocado_android.domain.repository.chatbot.ChatBotRepository
import com.example.avocado_android.domain.repository.librarypage.LibraryPageRepository
import com.example.avocado_android.domain.repository.mainpage.MainPageRepository
import com.example.avocado_android.domain.repository.wordpage.WordPageRepository
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

    @Singleton
    @Provides
    fun provideMainPageRepository(mainPageDataSource: MainPageDataSource) : MainPageRepository =
        MainPageRepositoryImpl(mainPageDataSource)

    @Singleton
    @Provides
    fun provideWordPageRepository(wordPageDataSource: WordPageDataSource) : WordPageRepository =
        WordPageRepositoryImpl(wordPageDataSource)

}