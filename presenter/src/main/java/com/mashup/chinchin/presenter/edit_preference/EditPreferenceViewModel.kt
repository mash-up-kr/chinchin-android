package com.mashup.chinchin.presenter.edit_preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

class EditPreferenceViewModel : ViewModel() {

    private val _questions = MutableLiveData<List<QuestionUiModel>>(emptyList())
    val questions: LiveData<List<QuestionUiModel>>
        get() = _questions

    fun initializeQuestions(questions: List<QuestionUiModel>) {
        _questions.value = questions
    }

    fun updateCheckedState(index: Int) {
        val newQuestions = _questions.value?.toMutableList()
        newQuestions?.let {
            val toggleCheckedState = !it[index].isChecked
            it[index] = it[index].copy(isChecked = toggleCheckedState)
        }
        _questions.value = newQuestions
    }

    fun getCheckedCardCount(): Int {
        val newQuestions = _questions.value?.toMutableList()
        val checkedCount = newQuestions?.let { it ->
            it.filter { question -> question.isChecked }.size
        }
        return checkedCount ?: 0
    }

    fun isEmptyCheckedCard(): Boolean =
        getCheckedCardCount() == 0

    fun getCheckedQuestionsToRemove() : List<QuestionUiModel> {
        val newQuestions = _questions.value?.toMutableList()
        newQuestions?.let {
            it.removeAll(it.filter { question -> question.isChecked })
        }
        return newQuestions?.toList() ?: emptyList()
    }
}