package com.mashup.chinchin.domain.repository

import com.mashup.chinchin.domain.model.Question

interface QuestionnaireRepository {
    /**
     * 답변할 질문들을 조회합니다.
     * aspect로 도메인이 나뉩니다.
     *
     * my: 내 답변
     * friend: 친구 답변
     * answer: 답변 해야할 질문들
     */
    suspend fun getQuestionnaire(
        questionnaireId: Long,
        aspect: String
    ): List<Question>

    /**
     * 답변을 완료합니다.
     * 답변 완료된 질문지를 전송합니다.
     */
    suspend fun sendReplyQuestionnaire(
        questionnaireId: Long,
        questionnaire: List<Question>
    ): Boolean

    /**
     * 친구에게 질문지를 전송합니다.
     */
    suspend fun sendQuestionnaire(friendId: Long, questionnaire: List<Question>): Boolean
}