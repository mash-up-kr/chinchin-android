package com.mashup.chinchin.presenter.edit_preference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

class EditPreferenceViewModel(questions: List<QuestionUiModel>) : ViewModel() {

    private val _questions = MutableLiveData<List<QuestionUiModel>>(emptyList())
    val questions: LiveData<List<QuestionUiModel>>
        get() = _questions

    init {
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
        val checkedCount = newQuestions?.filter { question -> question.isChecked }?.size ?: 0
        return checkedCount
    }

    fun isEmptyCheckedCard(): Boolean =
        getCheckedCardCount() == 0

    fun getRemovedQuestions(): List<QuestionUiModel> {
        val newQuestions = _questions.value?.toMutableList()
        newQuestions?.let {
           it.removeAll(it.filter { question -> question.isChecked })
        } ?: emptyList<QuestionUiModel>()
        val removedQuestion= newQuestions ?: emptyList()
        return removedQuestion
    }
}