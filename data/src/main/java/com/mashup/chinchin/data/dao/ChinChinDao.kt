package com.mashup.chinchin.data.dao

import androidx.room.*
import com.mashup.chinchin.data.dto.local.*

@Dao
interface ChinChinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFriend(friendEntity: FriendEntity)

    @Transaction
    @Query("select * from friend where friendId=(:friendId)")
    suspend fun getQuestionnaire(friendId: Long): FriendAndQuestionnaireEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionnaire(questionnaireEntity: QuestionnaireEntity)

    @Transaction
    @Query("select * from questionnaire where questionnaireId=(:questionnaireId)")
    suspend fun getQuestions(questionnaireId: Long): QuestionnaireWithQuestionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(questionEntity: QuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Delete
    suspend fun deleteQuestions(questions: List<QuestionEntity>)

}