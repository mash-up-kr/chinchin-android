package com.mashup.chinchin.presenter.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun handleKakaoCallback(token: OAuthToken?, error: Throwable?) {
        error?.let {
            handleError(it)
        }
        token?.let {
            Log.d("LoginViewModel", it.toString())
            sendUserToken(it)
        }
    }

    fun handleError(error: Throwable) {
        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
            errorMessage.value = "사용자가 명시적으로 취소"
        } else {
            errorMessage.value = "인증 에러 ${error.message}"
        }
    }

    private fun sendUserToken(token: OAuthToken) {
        // TODO: accessToken 서버에 보냄
    }
}
