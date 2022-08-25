package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class Friend (
    val id: Long,
    val profileNickname: String?,
    val profileThumbnailImage: String?,
    val birth: String?,
    val groupName: String?
): DomainModel
