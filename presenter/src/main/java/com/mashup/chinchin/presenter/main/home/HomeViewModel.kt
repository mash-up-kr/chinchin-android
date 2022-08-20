package com.mashup.chinchin.presenter.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.CreateNewGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val createNewGroupUseCase: CreateNewGroupUseCase,
) : ViewModel() {
    fun createNewGroup(groupName: String) {
        viewModelScope.launch {
            createNewGroupUseCase.invoke(groupName)
        }
    }
}
