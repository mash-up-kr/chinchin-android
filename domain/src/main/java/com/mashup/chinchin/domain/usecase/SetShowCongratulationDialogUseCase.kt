package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.LoginRepository
import javax.inject.Inject

class SetShowCongratulationDialogUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(isShowAble: Boolean) {
        loginRepository.setIsFirstEnter(isShowAble)
    }
}
