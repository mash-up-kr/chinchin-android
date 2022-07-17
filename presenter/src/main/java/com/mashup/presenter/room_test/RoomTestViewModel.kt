package com.mashup.presenter.room_test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.model.ChinChinModel
import com.mashup.domain.usecase.ChinChinUseCase
import com.mashup.domain.usecase.GetUserUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RoomTestViewModel @Inject constructor(
    private val getUserUrlUseCase: GetUserUrlUseCase,
    private val getChinChinUseCase: ChinChinUseCase,
) : ViewModel() {
    private val _items = mutableStateOf(emptyList<ChinChinModel>())
    val items: State<List<ChinChinModel>> = _items

    init {
        viewModelScope.launch {
            getChinChinUseCase.getChinChins()
                .collect { chinchins ->
                    _items.value = chinchins
                }
        }
    }

    fun addChinChin(name: String) {
        viewModelScope.launch {
            getChinChinUseCase.addChinChin(name)
        }
    }

    fun updateChinChin(uid: UUID) {
        val chinchin = _items.value.find { chinchin -> chinchin.uid == uid }
        chinchin?.let {
            viewModelScope.launch {
                getChinChinUseCase.updateChinChin(it.copy(isFriend = !it.isFriend))
            }
        }
    }

    fun deleteChinChin(uid: UUID) {
        val chinchin = _items.value.find { chinchin -> chinchin.uid == uid }
        chinchin?.let {
            viewModelScope.launch {
                getChinChinUseCase.deleteChinChin(it)
            }
        }
    }


}
