package com.mashup.chinchin.presenter.main.recommend_friend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.mashup.chinchin.domain.usecase.GetRecommendedFriendsUseCase
import com.mashup.chinchin.domain.usecase.SetKakaoTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendFriendsViewModel @Inject constructor(
    private val getRecommendedFriendsUseCase: GetRecommendedFriendsUseCase,
    private val setKakaoTokenUseCase: SetKakaoTokenUseCase,
): ViewModel() {
    private val _loginKakao = MutableLiveData<Unit>()
    val loginKakao: LiveData<Unit>
        get() = _loginKakao

    private fun getRecommendedFriends() {
        invalidKakaoToken()

        viewModelScope.launch {
            getRecommendedFriendsUseCase()
        }
    }

    /** 현재 accessToken이 만료되고 refresh 토큰이 살아있다면 갱신한다.
     * 그 외에는 다시 로그인한다. */
    private fun invalidKakaoToken() {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { _, error ->
                if (error != null) {
                    handleError(error)
                } else {
                    AuthApiClient.instance.refreshToken { token, refreshError ->
                        handleKakaoCallback(token, refreshError)
                    }
                }
            }
        } else {
            _loginKakao.value = Unit
        }
    }

    private fun handleError(error: Throwable) {
        if (error is KakaoSdkError && error.isInvalidTokenError()) {
            _loginKakao.value = Unit
        } else {
            Log.e("kakao login error", error.message.toString())
        }
    }

    fun handleKakaoCallback(token: OAuthToken?, error: Throwable?) {
        error?.let {
            handleError(it)
        }
        token?.let {
            Log.d("RecommendFriendsViewModel", it.toString())
            setKakaoTokenUseCase(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken,
            )
        }
    }
}
