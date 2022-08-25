package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.repository.QuestionnaireRepository
import javax.inject.Inject

class GetQuestionnareUseCase @Inject constructor(
    private val questionnaireRepository: QuestionnaireRepository
) {
    suspend operator fun invoke(
        questionnaireId: Long,
        aspect: String
    ): List<Question> {
        return questionnaireRepository.getQuestionnaire(
            questionnaireId = questionnaireId,
            aspect = aspect
        )
    }
}