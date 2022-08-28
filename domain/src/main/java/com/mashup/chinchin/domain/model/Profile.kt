package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class Profile (
    val id: Long,
    val groupId: Long,
    val groupName: String,
    val name: String,
    val dateOfBirth: String,
    val isMember: Boolean,
    val thumbnailImageUrl: String?,
    val kakaoId: String?,
): DomainModel
