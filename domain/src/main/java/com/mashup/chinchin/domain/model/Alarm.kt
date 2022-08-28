package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class Alarm(
    val friendId: Long,
    val questionnaireId: Long,
    val createdAt: Long,
    val type: String,
) : DomainModel
