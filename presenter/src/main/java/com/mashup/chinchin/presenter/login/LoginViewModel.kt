package com.mashup.chinchin.presenter.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.mashup.chinchin.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _isLoginSuccess =  MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean>
        get() = _isLoginSuccess

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun handleKakaoCallback(token: OAuthToken?, error: Throwable?) {
        error?.let {
            handleError(it)
        }
        token?.let {
            Log.d("LoginViewModel", it.toString())
            sendUserToken(it)
        }
    }

    private fun handleError(error: Throwable) {
        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
            _errorMessage.value = "사용자가 명시적으로 취소"
        } else {
            _errorMessage.value = "인증 에러 ${error.message}"
        }
    }

    private fun sendUserToken(token: OAuthToken) {
        viewModelScope.launch {
            val result = loginUseCase.invoke(
                accessToken = token.accessToken,
                refreshToken = token.refreshToken,
            )
            _isLoginSuccess.value = result
        }
    }
}
