package com.mashup.presenter.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.domain.usecase.CrimeTestUseCase
import com.mashup.domain.usecase.GetUserUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUrlUseCase: GetUserUrlUseCase,
    private val getCrimeTestUseCase: CrimeTestUseCase,
) : ViewModel() {
    fun hello() {
        viewModelScope.launch {
            Log.d("hi", getUserUrlUseCase.invoke("AnKyungMoo"))
        }
    }

    val items = getCrimeTestUseCase.getCrimes()

}
