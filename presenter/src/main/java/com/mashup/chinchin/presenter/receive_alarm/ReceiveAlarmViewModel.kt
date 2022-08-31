package com.mashup.chinchin.presenter.receive_alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.chinchin.domain.usecase.GetAlarmsUseCase
import com.mashup.chinchin.presenter.receive_alarm.model.ReceiveAlarmUiModel
import com.mashup.chinchin.presenter.receive_alarm.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiveAlarmViewModel @Inject constructor(
    private val getAlarmsUseCase: GetAlarmsUseCase,
): ViewModel() {
    private val _alarms = MutableLiveData<List<ReceiveAlarmUiModel>>()
    val alarms: LiveData<List<ReceiveAlarmUiModel>>
        get() = _alarms

    fun getAlarms() {
        viewModelScope.launch {
            _alarms.value = getAlarmsUseCase().map { it.toUiModel() }
        }
    }
}
