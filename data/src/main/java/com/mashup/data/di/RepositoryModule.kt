package com.mashup.data.di

import com.mashup.data.repository.GithubRepository
import com.mashup.data.repository.GithubRepositoryImpl
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
}
