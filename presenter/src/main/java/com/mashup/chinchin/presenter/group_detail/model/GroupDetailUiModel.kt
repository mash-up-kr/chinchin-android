package com.mashup.chinchin.presenter.group_detail.model

import com.mashup.chinchin.presenter.common.model.FriendUiModel

data class GroupDetailUiModel(
    val id: Long,
    val groupName: String,
    val friends: List<FriendUiModel>,
)

