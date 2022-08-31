package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class Friend(
    val id: Long,
    val name: String,
    val thumbnailImageUrl: String?,
) : DomainModel
