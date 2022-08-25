package com.mashup.chinchin.presenter.reply_preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.model.QuestionnaireAspectType
import com.mashup.chinchin.domain.usecase.GetQuestionnareUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReplyPreferenceViewModel @Inject constructor(
    private val getQuestionnareUseCase: GetQuestionnareUseCase
): ViewModel() {

    private val questionnaireId: Long = 1 // 연동할 때 getExtra해서 가져옵니다.

    init {
        getReplyQuestions(questionnaireId)
    }
    /**
     * 답변할 질문지 조회
     */
    fun getReplyQuestions(questionnaireId: Long) {
        viewModelScope.launch {
            getQuestionnareUseCase(
                questionnaireId = questionnaireId,
                aspect = QuestionnaireAspectType.Answer.value
            )
        }
    }
}