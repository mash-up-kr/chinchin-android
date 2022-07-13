package com.mashup.domain.di

import com.mashup.domain.usecase.ChinChinUseCase
import com.mashup.domain.usecase.GetUserUrlUseCase
import com.mashup.domain.usecase.IsAlreadyLoginUseCase
import com.mashup.domain.usecase.impl.ChinChinUseCaseImpl
import com.mashup.domain.usecase.impl.GetUserUrlUseCaseImpl
import com.mashup.domain.usecase.impl.IsAlreadyLoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetUserUrlUseCase(useCaseImpl: GetUserUrlUseCaseImpl): GetUserUrlUseCase

    @Binds
    fun bindIsAlreadyLoginUseCase(isAlreadyLoginUseCaseImpl: IsAlreadyLoginUseCaseImpl): IsAlreadyLoginUseCase

    @Binds
    fun bindChinChinUseCase(chinChinUseCaseImpl: ChinChinUseCaseImpl): ChinChinUseCase
}
