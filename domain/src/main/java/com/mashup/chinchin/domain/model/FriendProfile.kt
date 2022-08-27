package com.mashup.chinchin.domain.model

import com.mashup.chinchin.domain.base.DomainModel

data class FriendProfile(
    val profile: Profile,
    val myAnswers: List<Question>,
    val friendAnswers: List<Question>
): DomainModel
