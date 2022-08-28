package com.mashup.chinchin.presenter.group_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.GetGroupDetailUseCase
import com.mashup.chinchin.presenter.group_detail.model.GroupDetailUiModel
import com.mashup.chinchin.presenter.group_detail.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailViewModel @Inject constructor(
    private val GetGroupDetailUseCase: GetGroupDetailUseCase
) : ViewModel() {
    private val _groupDetail = MutableLiveData<GroupDetailUiModel>()
    val groupDetail: LiveData<GroupDetailUiModel>
        get() = _groupDetail

    fun getGroupDetail(groupId: Long) {
        viewModelScope.launch {
            _groupDetail.value = GetGroupDetailUseCase(groupId).toUiModel()
        }
    }
}