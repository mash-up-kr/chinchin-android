package com.mashup.data.di

import com.mashup.data.repository.ChinChinRepository
import com.mashup.data.repository.GithubRepository
import com.mashup.data.repository.LoginRepository
import com.mashup.data.repository.impl.ChinChinRepositoryImpl
import com.mashup.data.repository.impl.GithubRepositoryImpl
import com.mashup.data.repository.impl.LoginRepositoryImpl
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
    fun bindGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository

    @Binds
    @Singleton
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindChinChinRepository(chinchinRepositoryImpl: ChinChinRepositoryImpl): ChinChinRepository
}
