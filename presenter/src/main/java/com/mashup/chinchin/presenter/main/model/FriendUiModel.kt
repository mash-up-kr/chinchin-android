package com.mashup.chinchin.presenter.main.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 친구 Ui Model (임시)
 * FIXME: 백엔드 모델 나오면 변경
 */
@Parcelize
data class FriendUiModel(
    val name: String,
    val profileThumbnailUrl: String,
): Parcelable
