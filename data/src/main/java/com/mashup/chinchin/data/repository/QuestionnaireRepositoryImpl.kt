package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.remote.RemoteQuestionnaireDataSource
import com.mashup.chinchin.data.dto.remote.requestbody.QuestionRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendQuestionnaireRequestBody
import com.mashup.chinchin.data.dto.remote.requestbody.SendReplyQuestionnaireRequestBody
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

    override suspend fun sendReplyQuestionnaire(
        questionnaireId: Long,
        questionnaire: List<Question>
    ): Boolean {
        val requestBody = questionnaire.map {
            SendReplyQuestionnaireRequestBody(
                questionId = it.questionId,
                friendAnswer = it.answer
            )
        }
        return remoteQuestionnaireDataSource.sendReplyQuestionnaire(
            questionnaireId = questionnaireId,
            requestBody = requestBody
        ).isSuccess
    }

    override suspend fun sendQuestionnaire(friendId: Long, questionnaire: List<Question>): Boolean {
        val requestBody = SendQuestionnaireRequestBody(
            toFriendId = friendId,
            questionnaireDetails = questionnaire.map {
                QuestionRequestBody(
                    question = it.question,
                    myAnswer = it.answer
                )
            }
        )
        return remoteQuestionnaireDataSource.sendQuestionnaire(requestBody).isSuccess
    }
}