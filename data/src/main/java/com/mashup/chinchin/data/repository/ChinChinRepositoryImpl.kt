package com.mashup.chinchin.data.repository

import com.mashup.chinchin.data.datasource.local.LocalChinChinDataSource
import com.mashup.chinchin.data.dto.local.FriendEntity
import com.mashup.chinchin.data.dto.local.QuestionEntity
import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.model.TempSavedQuestionnaire
import com.mashup.chinchin.domain.repository.ChinChinRepository
import com.mashup.chinchin.domain.usecase.InsertFriendToDBParams
import com.mashup.chinchin.domain.usecase.InsertQuestionToDBParam
import javax.inject.Inject

class ChinChinRepositoryImpl @Inject constructor(
    private val localChinChinDataSource: LocalChinChinDataSource,
) : ChinChinRepository {

    override suspend fun insertFriend(friend: InsertFriendToDBParams) {
        val friendEntity = with(friend) {
            FriendEntity(
                friendId = friendId,
                name = name,
                dataOfBirth = dateOfBirth,
                groupId = groupId,
                thumbnailImageUrl = thumbnailImageUrl ?: "",
                kakaoId = kakaoId ?: "",
            )
        }
        localChinChinDataSource.insertFriend(friendEntity)
    }

    override suspend fun getQuestionnaire(friendId: Long): TempSavedQuestionnaire {
        val questionnaire = localChinChinDataSource.getQuestions(friendId)
        return with(questionnaire) {
            TempSavedQuestionnaire(
                questionnaireId = questionnaireEntity.questionnaireId,
                questions = questionEntityList.map {
                    Question(
                        questionId = it.questionId,
                        question = it.question,
                        answer = it.answer
                    )
                },
            )

        }
    }

    //TODO 호출하는 쪽 없음. 나중에 사용할 수 있으니깐 남겨두기..
    override suspend fun insertQuestion(question: InsertQuestionToDBParam) {
        localChinChinDataSource.insertQuestion(
            QuestionEntity(
                questionnaireId = question.questionnaireId,
                question = question.question,
                answer = question.answer,
            )
        )
    }

    override suspend fun insertQuestions(
        quesionnaireId: Long,
        questions: List<InsertQuestionToDBParam>
    ) {
        val questionEntities = questions.map { it ->
            with(it) {
                QuestionEntity(
                    questionnaireId = questionnaireId,
                    question = question,
                    answer = answer,
                ).also {
                    if (questionId != -1L) it.questionId = questionId
                }
            }
        }
        localChinChinDataSource.insertQuestions(quesionnaireId, questionEntities)
    }
}