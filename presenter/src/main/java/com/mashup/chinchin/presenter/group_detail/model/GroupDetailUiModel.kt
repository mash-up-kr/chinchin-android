package com.mashup.chinchin.presenter.group_detail.model

import com.mashup.chinchin.domain.model.GroupDetail
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.toUiModel

data class GroupDetailUiModel(
    val id: Long,
    val groupName: String,
    val friends: List<FriendUiModel>,
)

fun GroupDetail.toUiModel(): GroupDetailUiModel {
    return GroupDetailUiModel(
        id = groupId,
        groupName = groupName,
        friends = friendInfo.map { it.toUiModel() }
    )
}

