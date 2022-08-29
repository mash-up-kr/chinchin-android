package com.mashup.chinchin.presenter.connect_friend

import androidx.lifecycle.*
import com.mashup.chinchin.domain.usecase.GetFriendsUseCase
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectFriendViewModel @Inject constructor(
    private val getFriendsUseCase: GetFriendsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val connectFriend = savedStateHandle.get(ConnectFriendActivity.FRIEND) as? FriendUiModel

    private val _friends = MutableLiveData<List<FriendUiModel>>()
    val friends: LiveData<List<FriendUiModel>>
        get() = _friends

    fun getFriends() {
        viewModelScope.launch {
            val result = getFriendsUseCase()
            _friends.postValue(
                result.map { it.toUiModel() }
            )
        }
    }
}