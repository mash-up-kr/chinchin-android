package com.mashup.chinchin.presenter.friend_information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.AddFriendUseCase
import com.mashup.chinchin.domain.usecase.UpdateFriendUseCase
import com.mashup.chinchin.presenter.friend_information.model.FriendProfileType
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendInformationViewModel @Inject constructor(
    private val addFriendUseCase: AddFriendUseCase,
    private val updateFriendUseCase: UpdateFriendUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    /**
     * 현재 페이지 타입
     * 친구 추가: FriendProfileType.CREATE
     * 친구 수정: FriendProfileType.MODIFY
     */
    val profileType =
        savedStateHandle.get<FriendProfileType>(FriendInformationActivity.EXTRA_PROFILE_TYPE)
            ?: FriendProfileType.ADD

    // 신규 친구 추가 시 친구
    val newFriend = savedStateHandle.get<FriendUiModel>(FriendInformationActivity.NEW_FRIEND)

    val friendId = MutableLiveData<Long>()

    fun addFriend(friend: FriendUiModel) {
        viewModelScope.launch {
            val result = addFriendUseCase(friend.toDomainModel())

            friendId.postValue(result)
        }
    }

    fun updateFriend(friend: FriendUiModel) {
        viewModelScope.launch {
            val result = updateFriendUseCase(friend.toDomainModel())

            friendId.postValue(result)
        }
    }
}