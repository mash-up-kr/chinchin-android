package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.CommonRepository
import javax.inject.Inject

class IsShowCongratulationDialogUseCase @Inject constructor(
    private val commonRepository: CommonRepository,
) {
    operator fun invoke(): Boolean = commonRepository.getIsFirstEnter()
}
