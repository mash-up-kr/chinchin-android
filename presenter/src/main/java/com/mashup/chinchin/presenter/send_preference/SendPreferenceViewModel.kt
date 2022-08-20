package com.mashup.chinchin.presenter.send_preference

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

class SendPreferenceViewModel : ViewModel() {

    val questions = mutableStateListOf<QuestionUiModel>()

    fun onQuestionsChange(_questions: List<QuestionUiModel>) {
        questions.clear()
        questions.addAll(_questions)
    }

    fun addQuestion(question: QuestionUiModel) {
        questions.add(question)
    }

    fun changeQuestionByIndex(index: Int, questionText: String) {
        questions[index] = questions[index].copy(question = questionText)
    }

    fun changeAnswerByIndex(index: Int, answerText: String) {
        questions[index] = questions[index].copy(answer = answerText)
    }

}

