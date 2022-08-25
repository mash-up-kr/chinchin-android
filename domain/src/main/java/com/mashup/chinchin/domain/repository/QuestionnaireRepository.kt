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
}