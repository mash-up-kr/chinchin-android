package com.mashup.chinchin.presenter.send_preference

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.usecase.SendQuestionnaireUseCase
import com.mashup.chinchin.presenter.common.model.CategoryUiModel
import com.mashup.chinchin.presenter.common.model.KeywordQuestionUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendPreferenceViewModel @Inject constructor(
    private val sendQuestionnaireUseCase: SendQuestionnaireUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //TODO 라이브데이터 또는 Flow로 변경해야함
    private val friendId = savedStateHandle.get<Long>("EXTRA_FRIEND_ID") ?: 2

    val questions = mutableStateListOf<QuestionUiModel>()
    val isSendSuccess = MutableLiveData(false)

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

    fun sendQuestionnaire() {
        val questionnaire = questions.map {
            Question(
                questionId = 0,
                question = it.question,
                answer = it.answer
            )
        }
        viewModelScope.launch {
            val result = sendQuestionnaireUseCase(
                friendId = friendId,
                questionnaire = questionnaire
            )
            isSendSuccess.postValue(result)
        }
    }

    fun getCategoryList(): MutableList<CategoryUiModel> {
        val categoryList = mutableListOf<CategoryUiModel>()
        val preferences = CategoryUiModel(
            category = "취향 키워드",
            keywords = listOf(
                KeywordQuestionUiModel(keyword = "좋아하는 음식", question = "좋아하는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "싫어하는 음식", question = "싫어하는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 향", question = "좋아하는 향은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 옷 브랜드", question = "좋아하는 옷 브랜드는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 꽃", question = "좋아하는 꽃은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "좋아하는 술", question = "좋아하는 술은 무엇인가요?"),
            )
        )

        val privateInformation = CategoryUiModel(
            category = "개인 정보",
            keywords = listOf(
                KeywordQuestionUiModel(keyword = "MBTI", question = "MBTI는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "못 먹는 음식", question = "못 먹는 음식은 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "옷 사이즈", question = "옷 사이즈는 무엇인가요?"),
                KeywordQuestionUiModel(keyword = "신발 사이즈", question = "신발 사이즈는 어떻게 되나요?"),
                KeywordQuestionUiModel(keyword = "활동 지역", question = "주로 활동하는 지역은 어디인가요?"),
                KeywordQuestionUiModel(
                    keyword = "최애 영화",
                    question = "지금까지 봤던 영화 중 가장 좋았던 영화는 어떤거야?"
                ),
            )
        )
        categoryList.add(preferences)
        categoryList.add(privateInformation)

        return categoryList
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

