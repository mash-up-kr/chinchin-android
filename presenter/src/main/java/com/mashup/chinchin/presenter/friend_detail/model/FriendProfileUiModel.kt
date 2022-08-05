package com.mashup.chinchin.presenter.friend_detail.model

data class FriendProfileUiModel(
    val profileUrl: String,
    val name: String,
    val birthday: String, // Todo need to chagge dateType
    val groupName: String
)
