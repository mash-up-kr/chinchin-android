package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionUiModel(
    val question: String,
    val answer: String = "",
    val isChecked: Boolean = false,
) : Parcelable
