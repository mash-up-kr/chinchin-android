package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupUiModel(
    val groupId: Long?,
    val groupName: String?
): Parcelable
