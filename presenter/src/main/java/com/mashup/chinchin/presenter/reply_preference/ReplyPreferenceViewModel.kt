package com.mashup.chinchin.presenter.reply_preference

import androidx.lifecycle.*
import com.mashup.chinchin.domain.model.QuestionnaireAspectType
import com.mashup.chinchin.domain.usecase.GetQuestionnareUseCase
import com.mashup.chinchin.domain.usecase.SendReplyQuestionnaireUseCase
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReplyPreferenceViewModel @Inject constructor(
    private val getQuestionnareUseCase: GetQuestionnareUseCase,
    private val sendReplyQuestionnaireUseCase: SendReplyQuestionnaireUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val questionnaireId = savedStateHandle.get<Long>(QUESTIONNAIRE_ID)

    private val _fromFriendName = MutableLiveData<String>()
    val fromFriendName: LiveData<String>
        get() = _fromFriendName

    private val _questionnaire =  MutableLiveData<List<QuestionUiModel>>()
    val questionnaire: LiveData<List<QuestionUiModel>>
        get() = _questionnaire

    val isSendSuccess = MutableLiveData(false)

    init {
        getReplyQuestions(questionnaireId)
    }

    /**
     * 답변할 질문지 조회
     */
    private fun getReplyQuestions(questionnaireId: Long?) {
        viewModelScope.launch {
            questionnaireId?.let {
                val result = getQuestionnareUseCase(
                    questionnaireId = it,
                    aspect = QuestionnaireAspectType.Answer.value
                )
                _fromFriendName.postValue(result.memberName)
                _questionnaire.postValue(
                    result.questionnaire.map {
                        QuestionUiModel.fromDomainModel(it)
                    }
                )
            }
        }
    }

    /**
     * 답변 완료 질문지 보내기
     */
    fun sendReplyQuestionnaire(questionnaireId: Long, questions: List<QuestionUiModel>) {
        val questionnaire = questions.map { it.toDomainModel() }

        viewModelScope.launch {
            val result = sendReplyQuestionnaireUseCase(
                questionnaireId = questionnaireId,
                questionnaire = questionnaire
            )
            isSendSuccess.postValue(result)
        }
    }

    fun updateCheckedState(index: Int) {
        val newQuestions = _questionnaire.value?.toMutableList()
        newQuestions?.let {
            val toggleCheckedState = !it[index].isChecked
            it[index] = it[index].copy(isChecked = toggleCheckedState)
        }
        _questionnaire.value = newQuestions
    }

    fun areCompletedReplies(): Boolean {
        return _questionnaire.value?.filter { it.isChecked }?.size == _questionnaire.value?.size
    }

    companion object {
        const val QUESTIONNAIRE_ID = "QUESTIONNAIRE_ID"
    }

}