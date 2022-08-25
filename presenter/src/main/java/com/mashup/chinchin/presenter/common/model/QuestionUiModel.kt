package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import com.mashup.chinchin.domain.model.Question
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuestionUiModel(
    val questionId: Long,
    val question: String,
    val answer: String = "",
    val isChecked: Boolean = false,
) : Parcelable {
    fun toDomainModel(): Question {
        return Question(
            questionId = questionId,
            question = question,
            answer = answer
        )
    }
}
