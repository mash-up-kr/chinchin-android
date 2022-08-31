package com.mashup.chinchin.presenter.send_preference

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.model.Question
import com.mashup.chinchin.domain.usecase.SendQuestionnaireUseCase
import com.mashup.chinchin.presenter.common.model.CategoryUiModel
import com.mashup.chinchin.presenter.common.model.KeywordQuestionUiModel
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_FRIEND_ID
import com.mashup.chinchin.presenter.send_preference.SendPreferenceActivity.Companion.EXTRA_FRIEND_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendPreferenceViewModel @Inject constructor(
    private val sendQuestionnaireUseCase: SendQuestionnaireUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    //TODO 라이브데이터 또는 Flow로 변경해야함
    private val _questions = MutableLiveData<List<QuestionUiModel>>(emptyList())
    val questions: LiveData<List<QuestionUiModel>>
        get() = _questions

    private val friendId = savedStateHandle.get<Long>(EXTRA_FRIEND_ID) ?: 2
    val friendName = savedStateHandle.get<String>(EXTRA_FRIEND_NAME) ?: ""

    val isSendSuccess = MutableLiveData(false)

    fun changeQuestions(newQuestions: List<QuestionUiModel>) {
        _questions.value = newQuestions
    }

    fun addQuestion(question: QuestionUiModel) {
        val newQuestion = _questions.value?.toMutableList()
        newQuestion?.add(question)
        _questions.value = newQuestion
    }

    fun changeQuestionByIndex(index: Int, questionText: String) {
        val newQuestion = _questions.value?.toMutableList()
        newQuestion?.get(index)
            ?.let { newQuestion.set(index, it.copy(question = questionText)) }
        _questions.value = newQuestion
    }

    fun changeAnswerByIndex(index: Int, answerText: String) {
        val newQuestion = _questions.value?.toMutableList()
        newQuestion?.get(index)
            ?.let { newQuestion?.set(index, it.copy(answer = answerText)) }
        _questions.value = newQuestion
    }

    fun sendQuestionnaire() {
        val questionnaire = _questions.value?.map {
            Question(
                questionId = 0,
                question = it.question,
                answer = it.answer
            )
        } ?: emptyList()
        viewModelScope.launch {
            val result = sendQuestionnaireUseCase(
                friendId = friendId,
                questionnaire = questionnaire
            )
            isSendSuccess.postValue(result)
        }
    }

    fun getCategoryList(): List<CategoryUiModel> {
        // TODO: 나중에 LocalDataSource에서 가져올수있도록 수정하면 좋을 것 같아요.
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

       return mutableListOf<CategoryUiModel>().apply {
           add(preferences)
           add(privateInformation)
       }.toList()
    }

}

