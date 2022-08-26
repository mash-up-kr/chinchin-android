package com.mashup.chinchin.data.di

import com.mashup.chinchin.data.repository.GroupRepositoryImpl
import com.mashup.chinchin.data.repository.LoginRepositoryImpl
import com.mashup.chinchin.data.repository.RecommendedFriendRepositoryImpl
import com.mashup.chinchin.domain.repository.GroupRepository
import com.mashup.chinchin.domain.repository.LoginRepository
import com.mashup.chinchin.domain.repository.RecommendedFriendRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

    @Binds
    @Singleton
    fun bindRecommendedFriendRepository(
        recommendedFriendRepositoryImpl: RecommendedFriendRepositoryImpl,
    ): RecommendedFriendRepository
}
