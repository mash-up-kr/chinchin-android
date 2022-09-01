package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.CommonRepository
import javax.inject.Inject

class SetShowCongratulationDialogUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
) {
    operator fun invoke(isShowAble: Boolean) {
        commonRepository.setIsFirstEnter(isShowAble)
    }
}
