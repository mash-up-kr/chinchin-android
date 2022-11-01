package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.TempSavedQuestionnaire
import com.mashup.chinchin.domain.repository.ChinChinRepository
import javax.inject.Inject

class GetTempSavedQuestionnaireUseCase @Inject constructor(
    private val chinChinRepository: ChinChinRepository,
) {
    suspend operator fun invoke(friendId: Long): TempSavedQuestionnaire =
        chinChinRepository.getQuestionnaire(friendId)
}