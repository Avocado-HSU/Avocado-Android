package com.example.avocado_android.data.di

import com.example.avocado_android.utils.token.AccessTokenInterceptor
import com.example.avocado_android.utils.token.CookieInterceptor
import com.example.avocado_android.utils.token.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ApiRetrofit

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideAccessTokenInterceptor(tokenManager: TokenManager): AccessTokenInterceptor {
        return AccessTokenInterceptor(tokenManager)
    }

    @Singleton
    @Provides
    fun provideCookieInterceptor(tokenManager: TokenManager): CookieInterceptor {
        return CookieInterceptor(tokenManager)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        accessTokenInterceptor: AccessTokenInterceptor,
        cookieInterceptor: CookieInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(accessTokenInterceptor)
            .addInterceptor(cookieInterceptor)
            .build()
    }

    @ApiRetrofit
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl("http://avocado-hsu.kro.kr")
            .build()
    }
}
