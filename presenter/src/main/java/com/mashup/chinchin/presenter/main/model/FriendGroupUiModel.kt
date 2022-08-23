package com.mashup.chinchin.presenter.main.model

import android.os.Parcelable
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import kotlinx.parcelize.Parcelize

/**
 * 친구 그룹 Ui Model (임시)
 * FIXME: 백엔드 모델 나오면 변경
 */
@Parcelize
data class FriendGroupUiModel(
    val name: String,
    val friends: List<FriendUiModel>
): Parcelable
