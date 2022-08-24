package com.mashup.chinchin.presenter.edit_preference

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

class EditPreferenceViewModel : ViewModel() {
    //TODO 라이브 데이터로 변경해야함
    val questions = mutableStateListOf<QuestionUiModel>()

    fun initializeQuestions( _questions :List<QuestionUiModel>){
        questions.clear()
        questions.addAll(_questions)
    }

    fun updateCheckedState(index: Int){
        val toggleCheckedState = !questions[index].isChecked
        questions[index] = questions[index].copy(isChecked = toggleCheckedState)
    }

    fun getCheckedCardCount(): Int {
        return questions.filter{it.isChecked}.size
    }

    fun isEmptyCheckedCard(): Boolean =
        getCheckedCardCount() == 0

    fun removeCheckedQuestions(){
        questions.removeAll(questions.filter{it.isChecked})
    }
}