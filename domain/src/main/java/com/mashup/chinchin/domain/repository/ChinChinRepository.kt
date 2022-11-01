package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.TempSavedQuestionnaire
import com.mashup.chinchin.domain.usecase.InsertFriendToDBParams
import com.mashup.chinchin.domain.usecase.InsertQuestionToDBParam

interface ChinChinRepository {
    suspend fun insertFriend(friend: InsertFriendToDBParams)
    suspend fun getQuestionnaire(friendId: Long): TempSavedQuestionnaire
    suspend fun insertQuestion(question: InsertQuestionToDBParam)
    suspend fun insertQuestions(questionnaireId: Long, questions: List<InsertQuestionToDBParam>)
}