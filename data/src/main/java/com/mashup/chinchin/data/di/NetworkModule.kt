package com.mashup.chinchin.data.di

import com.mashup.chinchin.data.network.TokenInterceptor
import com.mashup.chinchin.data.service.ChinChinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideChinChinService(retrofit: Retrofit): ChinChinService =
        retrofit.create(ChinChinService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.chinchin.kr/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(tokenInterceptor)
            .build()

    companion object {
//        val networkFlipperPlugin = NetworkFlipperPlugin()
    }
}
