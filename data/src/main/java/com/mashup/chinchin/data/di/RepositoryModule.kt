package com.mashup.chinchin.data.di

import com.mashup.chinchin.data.repository.*
import com.mashup.chinchin.domain.repository.*
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
    fun bindQuestionnaireRepository(questionnaireRepositoryImpl: QuestionnaireRepositoryImpl): QuestionnaireRepository

    @Binds
    @Singleton
    fun bindFriendProfileRepository(friendProfileRepositoryImpl: FriendRepositoryImpl): FriendRepository

    @Binds
    @Singleton
    fun bindAlarmRepository(alarmRepositoryImpl: AlarmRepositoryImpl): AlarmRepository

    @Binds
    @Singleton
    fun bindRecommendedFriendRepository(
        recommendedFriendRepositoryImpl: RecommendedFriendRepositoryImpl,
    ): RecommendedFriendRepository

    @Binds
    @Singleton
    fun bindChinChinRepository(chinchinRepositoryImpl: ChinChinRepositoryImpl): ChinChinRepository
}
