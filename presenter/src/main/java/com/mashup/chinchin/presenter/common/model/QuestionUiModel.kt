package com.mashup.chinchin.presenter.common.model

import android.os.Parcelable
import com.mashup.chinchin.domain.model.Question
import kotlinx.android.parcel.Parcelize

/**
 * @questionId: questionId를 아직 부여받지 못한 질문지 보낼때 id는 -1, 이외에는 항상 questionId를 갖고 있습니다.
 */
@Parcelize
data class QuestionUiModel(
    val questionId: Long = -1,
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

    companion object {
        fun fromDomainModel(question: Question): QuestionUiModel {
            return QuestionUiModel(
                questionId = question.questionId,
                question = question.question,
                answer = question.answer,
                isChecked = false
            )
        }
    }
}
