package com.mashup.presenter.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.model.ChinChinModel
import com.mashup.domain.usecase.ChinChinUseCase
import com.mashup.domain.usecase.GetUserUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUrlUseCase: GetUserUrlUseCase,
    private val getChinChinUseCase: ChinChinUseCase,
) : ViewModel() {
    fun hello() {
        viewModelScope.launch {
            Log.d("hi", getUserUrlUseCase.invoke("AnKyungMoo"))
        }
    }

    val items:LiveData<List<ChinChinModel>> = getChinChinUseCase.getChinChins()

}
