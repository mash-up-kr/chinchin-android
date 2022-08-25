package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteQuestionnaireDataSource
import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.repository.QuestionnaireRepository
import javax.inject.Inject

class QuestionnaireRepositoryImpl @Inject constructor(
    private val remoteQuestionnaireDataSource: RemoteQuestionnaireDataSource
) : QuestionnaireRepository {
    override suspend fun getQuestionnaire(
        questionnaireId: Long,
        aspect: String
    ): List<Question> {
        return remoteQuestionnaireDataSource.getQuestionnaire(
            questionnaireId = questionnaireId,
            aspect = aspect
        ).map { it.toDomainModel() }
    }
}