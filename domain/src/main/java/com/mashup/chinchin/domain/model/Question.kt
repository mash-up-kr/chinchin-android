package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class Question(
    val questionId: Long,
    val question: String,
    val answer: String
): DomainModel
