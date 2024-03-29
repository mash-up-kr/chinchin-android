package com.mashup.chinchin.data.di

import com.mashup.chinchin.data.repository.FriendRepositoryImpl
import com.mashup.chinchin.data.repository.AlarmRepositoryImpl
import com.mashup.chinchin.data.repository.GroupRepositoryImpl
import com.mashup.chinchin.data.repository.LoginRepositoryImpl
import com.mashup.chinchin.data.repository.QuestionnaireRepositoryImpl
import com.mashup.chinchin.domain.repository.FriendRepository
import com.mashup.chinchin.domain.repository.AlarmRepository
import com.mashup.chinchin.data.repository.RecommendedFriendRepositoryImpl
import com.mashup.chinchin.domain.repository.GroupRepository
import com.mashup.chinchin.domain.repository.LoginRepository
import com.mashup.chinchin.domain.repository.RecommendedFriendRepository
import com.mashup.chinchin.domain.repository.QuestionnaireRepository
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
}
