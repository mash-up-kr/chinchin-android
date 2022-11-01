package com.mashup.chinchin.presenter.friend_information

import androidx.lifecycle.*
import com.mashup.chinchin.domain.usecase.*
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.friend_information.model.FriendProfileType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendInformationViewModel @Inject constructor(
    private val addFriendUseCase: AddFriendUseCase,
    private val updateFriendUseCase: UpdateFriendUseCase,
    private val insertFriendToDBUseCase: InsertFriendToDBUseCase,
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

            val friendId = addFriendUseCase(param)
            _friendId.postValue(friendId)
            val paramToDB = InsertFriendToDBParams(
                friendId = friendId,
                name = friend.name,
                groupId = friend.group.groupId,
                dateOfBirth = friend.birthday,
                thumbnailImageUrl = friend.profileUrl,
                kakaoId = friend.kakaoId
            )
            insertFriendToDBUseCase(paramToDB)
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