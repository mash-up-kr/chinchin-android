package com.mashup.chinchin.presenter.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.CreateNewGroupUseCase
import com.mashup.chinchin.domain.usecase.GetGroupsUseCase
import com.mashup.chinchin.domain.usecase.IsAlarmExistUseCase
import com.mashup.chinchin.presenter.main.model.FriendGroupUiModel
import com.mashup.chinchin.presenter.main.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createNewGroupUseCase: CreateNewGroupUseCase,
    private val GetGroupsUseCase: GetGroupsUseCase,
    private val isAlarmExistUseCase: IsAlarmExistUseCase,
) : ViewModel() {
    private val _groups = MutableLiveData<FriendGroupUiModel>()
    val groups: LiveData<FriendGroupUiModel>
        get() = _groups
    private val _isExistAlarm = MutableLiveData<Boolean>()
    val isExistAlarm: LiveData<Boolean>
        get() = _isExistAlarm

    fun createNewGroup(groupName: String) {
        viewModelScope.launch {
            createNewGroupUseCase.invoke(groupName)
        }
    }

    fun getGroups() {
        viewModelScope.launch {
            _groups.value = GetGroupsUseCase().toUiModel()
        }
    }

    fun checkAlarmExist() {
        viewModelScope.launch {
            _isExistAlarm.value = isAlarmExistUseCase()
        }
    }
}
