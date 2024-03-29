package com.mashup.chinchin.presenter.friend_information

import androidx.lifecycle.*
import com.mashup.chinchin.domain.usecase.AddFriendParams
import com.mashup.chinchin.domain.usecase.AddFriendUseCase
import com.mashup.chinchin.domain.usecase.UpdateFriendParams
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

    // 친구 정보가 일부 있거나, 친구를 수정할 때
    val friend = savedStateHandle.get<FriendUiModel>(FriendInformationActivity.EXTRA_FRIEND)

    // api 결과로 받아오는 friendId
    private val _friendId = MutableLiveData<Long>()
    val friendId: LiveData<Long>
        get() = _friendId

    fun addFriend(friend: FriendUiModel) {
        viewModelScope.launch {
            val param = AddFriendParams(
                name = friend.name ?: throw Exception("name shouldn't be null"),
                groupId = friend.group?.groupId ?: throw Exception("groupId shouldn't be null"),
                dateOfBirth = friend.birthday ?: throw Exception("birthday shouldn't be null"),
                thumbnailImageUrl = friend.profileUrl,
                kakaoId = friend.kakaoId
            )
            val result = addFriendUseCase(param)

            _friendId.postValue(result)
        }
    }

    fun updateFriend(newFriend: FriendUiModel) {
        viewModelScope.launch {
            val param = UpdateFriendParams(
                name = newFriend.name ?: throw Exception("name shouldn't be null"),
                groupId = newFriend.group?.groupId ?: throw Exception("groupId shouldn't be null"),
                dateOfBirth = newFriend.birthday ?: throw Exception("birthday shouldn't be null"),
                thumbnailImageUrl = newFriend.profileUrl,
                kakaoId = newFriend.kakaoId
            )
            friend?.id?.let {
                val result = updateFriendUseCase(it, param)
                _friendId.postValue(result)
            }
        }
    }
}