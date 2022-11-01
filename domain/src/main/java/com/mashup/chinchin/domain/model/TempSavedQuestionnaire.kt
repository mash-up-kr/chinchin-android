package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class TempSavedQuestionnaire(
    val questionnaireId: Long,
    val questions: List<Question> = emptyList(),
) : DomainModel