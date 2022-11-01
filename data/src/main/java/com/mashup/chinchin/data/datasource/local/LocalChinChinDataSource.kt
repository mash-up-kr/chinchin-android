package com.mashup.chinchin.data.datasource.local

import androidx.room.Transaction
import com.mashup.chinchin.data.dao.ChinChinDao
import com.mashup.chinchin.data.dto.local.*
import javax.inject.Inject

class LocalChinChinDataSource @Inject constructor(
    private val chinChinDao: ChinChinDao,
) {
    @Transaction
    suspend fun insertFriend(friendEntity: FriendEntity) {
        //when new friend add, friend must have single questionnaire
        chinChinDao.insertFriend(friendEntity)
        chinChinDao.insertQuestionnaire(QuestionnaireEntity(friendEntity.friendId))
    }

    suspend fun getQuestions(questionnaireId: Long): QuestionnaireWithQuestionEntity {
        return chinChinDao.getQuestions(questionnaireId)
    }

    suspend fun insertQuestions(questionnaireId: Long, questions: List<QuestionEntity>) {
        val questionEntities = chinChinDao.getQuestions(questionnaireId)
        val questionEntityList = questionEntities.questionEntityList
        val sameQuestions =
            questionEntityList.filter { questions.map { it.questionId }.contains(it.questionId) }
        val differentQuestions = questionEntityList.minus(sameQuestions)
        //update question list
        chinChinDao.insertQuestions(questions)

        //delete question list
        chinChinDao.deleteQuestions(differentQuestions)
    }

    //TODO 나중에 사용할 수 있으므로 남겨두기
    suspend fun getQuestionnaire(friendId: Long): FriendAndQuestionnaireEntity {
        return chinChinDao.getQuestionnaire(friendId)
    }

    //TODO 나중에 사용할 수 있으므로 남겨두기
    suspend fun insertQuestion(questionEntity: QuestionEntity) {
        chinChinDao.insertQuestion(questionEntity)
    }
}