package com.mashup.chinchin.presenter.friend_detail

import android.util.Log
import androidx.lifecycle.*
import com.mashup.chinchin.domain.usecase.GetFriendProfileUseCase
import com.mashup.chinchin.domain.usecase.GetTempSavedQuestionnaireUseCase
import com.mashup.chinchin.presenter.common.model.QuestionUiModel
import com.mashup.chinchin.presenter.friend_detail.model.FriendProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(
    private val getFriendProfileUseCase: GetFriendProfileUseCase,
    private val getTempSavedQuestionnaireUseCase: GetTempSavedQuestionnaireUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val friendId = savedStateHandle.get<Long>("EXTRA_FRIEND_ID") ?: 2
    private val _friendProfile = MutableLiveData<FriendProfileUiModel>()
    val friendProfile: LiveData<FriendProfileUiModel>
        get() = _friendProfile

    private val _tempSavedQuestionnaire = MutableLiveData<List<QuestionUiModel>>()
    val tempSavedQuestionnaire: LiveData<List<QuestionUiModel>>
        get() = _tempSavedQuestionnaire

    private val _isTempSavedQuestionnaireUpdated = MutableLiveData(false)
    val isTempSavedQuestionnaireUpdated: LiveData<Boolean>
        get() = _isTempSavedQuestionnaireUpdated

    init {
        getFriendProfile(friendId)
        getTempSavedQuestionnaire(friendId)
    }

    private fun getFriendProfile(friendId: Long) {
        viewModelScope.launch {
            val result = getFriendProfileUseCase(friendId = friendId)

            _friendProfile.postValue(
                FriendProfileUiModel.fromDomainModel(result)
            )
        }
    }

    private fun getTempSavedQuestionnaire(friendId: Long) {
        viewModelScope.launch {
            val result = getTempSavedQuestionnaireUseCase(friendId = friendId)
            Log.i(TAG, "getTempSavedQuestionnaire: result= $result")
            if (result.questions.isNotEmpty()) {
                _tempSavedQuestionnaire.value =
                    result.questions.map { QuestionUiModel.fromDomainModel(it) }
                _isTempSavedQuestionnaireUpdated.value = true
            }
        }
    }

    companion object {
        const val TAG = "FriendDetailViewModel"
    }
}