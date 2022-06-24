package com.mashup.chinchin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.GetUserUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUrlUseCase: GetUserUrlUseCase,
) : ViewModel() {
    fun hello() {
        viewModelScope.launch {
            Log.d("hi", getUserUrlUseCase.invoke("AnKyungMoo"))
        }
    }
}
