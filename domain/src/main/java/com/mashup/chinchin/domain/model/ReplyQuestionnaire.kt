package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class ReplyQuestionnaire(
    val memberName: String,
    val questionnaire: List<Question>,
) : DomainModel