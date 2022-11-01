package com.mashup.chinchin.domain.usecase

import com.mashup.chinchin.domain.repository.ChinChinRepository
import javax.inject.Inject

class InsertQuestionToDBUseCase @Inject constructor(
    private val chinChinRepository: ChinChinRepository,
) {
    suspend operator fun invoke(question: InsertQuestionToDBParam) =
        chinChinRepository.insertQuestion(question)
}

data class InsertQuestionToDBParam(
    val questionId: Long,
    val questionnaireId: Long,
    val question: String,
    val answer: String,
)