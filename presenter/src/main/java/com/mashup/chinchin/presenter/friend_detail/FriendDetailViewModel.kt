package com.mashup.chinchin.presenter.friend_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.GetFriendProfileUseCase
import com.mashup.chinchin.presenter.friend_detail.model.FriendProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendDetailViewModel @Inject constructor(
    private val getFriendProfileUseCase: GetFriendProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val friendId = savedStateHandle.get<Long>("EXTRA_FRIEND_ID") ?: 2
    val friendProfile = MutableLiveData<FriendProfileUiModel>()

    init {
        getFriendProfile(friendId)
    }

    private fun getFriendProfile(friendId: Long) {
        viewModelScope.launch {
            val result = getFriendProfileUseCase(friendId = friendId)

            friendProfile.postValue(
                FriendProfileUiModel.fromDomainModel(result)
            )
        }
    }
}