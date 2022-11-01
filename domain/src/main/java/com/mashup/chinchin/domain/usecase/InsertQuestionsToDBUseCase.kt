package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.ChinChinRepository
import javax.inject.Inject

class InsertQuestionsToDBUseCase @Inject constructor(
    private val chinChinRepository: ChinChinRepository,
) {
    suspend operator fun invoke(questionnaireId: Long, questions: List<InsertQuestionToDBParam>) =
        chinChinRepository.insertQuestions(questionnaireId, questions)
}