package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.repository.QuestionnaireRepository
import javax.inject.Inject

class SendQuestionnaireUseCase @Inject constructor(
    private val questionnaireRepository: QuestionnaireRepository
) {
    suspend operator fun invoke(
        friendId: Long,
        questionnaire: List<Question>
    ): Boolean {
        return questionnaireRepository.sendQuestionnaire(friendId, questionnaire)
    }
}