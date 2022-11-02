package com.mashup.chinchin.presenter.friend_detail

import androidx.lifecycle.*
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
    private val _friendProfile = MutableLiveData<FriendProfileUiModel>()
    val friendProfile: LiveData<FriendProfileUiModel>
        get() = _friendProfile

    init {
        getFriendProfile(friendId)
    }

    fun getFriendProfile(friendId: Long) {
        viewModelScope.launch {
            val result = getFriendProfileUseCase(friendId = friendId)
            _friendProfile.postValue(
                FriendProfileUiModel.fromDomainModel(result)
            )
        }
    }
}