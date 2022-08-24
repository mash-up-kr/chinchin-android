package com.mashup.chinchin.presenter.send_preference

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel

class SendPreferenceViewModel : ViewModel() {
    //TODO 라이브데이터 또는 Flow로 변경해야함
    val questions = mutableStateListOf<QuestionUiModel>()

    fun changeQuestions(_questions: List<QuestionUiModel>) {
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

    //참고) livedata 사용 코드
    /*  val _questionsLiveData =
          MutableLiveData<MutableList<QuestionUiModel>>(mutableListOf(QuestionUiModel("dd", "ddd")))

       val questions: LiveData<MutableList<QuestionUiModel>> = _questionsLiveData

       fun changeQuestions(newQuestions: MutableList<QuestionUiModel>) {
           _questionsLiveData.value = newQuestions
           Log.i("SendPreferenceViewModel", "changeQuestions: ${_questionsLiveData.value}")
       }

       fun addQuestion(question: QuestionUiModel) {
           _questionsLiveData.value?.add(question)
           Log.i("SendPreferenceViewModel", "addQuestion: ${_questionsLiveData.value}")
       }

       fun changeQuestionByIndex(index: Int , questionText: String){
           _questionsLiveData.value?.get(index)
               ?.let { _questionsLiveData.value?.set(index, it.copy(question = questionText)) }
           Log.i("SendPreferenceViewModel", "changeQuestionByIndex: ${_questionsLiveData.value}")

       }

       fun changeAnswerByIndex(index: Int , questionText: String){
           _questionsLiveData.value?.get(index)
               ?.let { _questionsLiveData.value?.set(index, it.copy(question = questionText)) }
           Log.i("SendPreferenceViewModel", "changeAnswerByIndex: ${_questionsLiveData.value}")
       }*/

}

