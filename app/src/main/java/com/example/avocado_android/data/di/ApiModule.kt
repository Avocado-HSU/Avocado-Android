package com.example.avocado_android.data.di

import com.example.avocado_android.data.remote.ChatBotApi
import com.example.avocado_android.data.remote.LibraryPageApi
import com.example.avocado_android.data.remote.MainPageApi
import com.example.avocado_android.data.remote.MemberControlApi
import com.example.avocado_android.data.remote.WordPageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideChatBotApi(
        @NetworkModule.ApiRetrofit retrofit: Retrofit
    ): ChatBotApi = retrofit.create(ChatBotApi::class.java)

    @Provides
    @Singleton
    fun provideLibraryApi(
        @NetworkModule.ApiRetrofit retrofit: Retrofit
    ): LibraryPageApi = retrofit.create(LibraryPageApi::class.java)

    @Provides
    @Singleton
    fun provideMainPageApi(
        @NetworkModule.ApiRetrofit retrofit: Retrofit
    ): MainPageApi = retrofit.create(MainPageApi::class.java)

    @Provides
    @Singleton
    fun provideWordPageApi(
        @NetworkModule.ApiRetrofit retrofit: Retrofit
    ): WordPageApi = retrofit.create(WordPageApi::class.java)

    @Provides
    @Singleton
    fun provideMemberControlApi(
        @NetworkModule.ApiRetrofit retrofit: Retrofit
    ) : MemberControlApi = retrofit.create(MemberControlApi::class.java)
}