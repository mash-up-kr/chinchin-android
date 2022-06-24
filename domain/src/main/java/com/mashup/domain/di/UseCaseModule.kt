package com.mashup.domain.di

import com.mashup.domain.usecase.GetUserUrlUseCase
import com.mashup.domain.usecase.GetUserUrlUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetUserUrlUseCase(useCaseImpl: GetUserUrlUseCaseImpl): GetUserUrlUseCase
}
