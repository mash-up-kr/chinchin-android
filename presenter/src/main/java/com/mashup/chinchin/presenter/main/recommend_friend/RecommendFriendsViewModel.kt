package com.mashup.chinchin.presenter.main.recommend_friend

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.mashup.chinchin.domain.usecase.GetKakaoFriendsPermissionUseCase
import com.mashup.chinchin.domain.usecase.GetRecommendedFriendsUseCase
import com.mashup.chinchin.domain.usecase.SetKakaoFriendsPermissionUseCase
import com.mashup.chinchin.domain.usecase.SetKakaoTokenUseCase
import com.mashup.chinchin.presenter.common.model.FriendUiModel
import com.mashup.chinchin.presenter.common.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendFriendsViewModel @Inject constructor(
    private val getRecommendedFriendsUseCase: GetRecommendedFriendsUseCase,
    private val setKakaoTokenUseCase: SetKakaoTokenUseCase,
    private val getKakaoFriendsPermissionUseCase: GetKakaoFriendsPermissionUseCase,
    private val setKakaoFriendsPermissionUseCase: SetKakaoFriendsPermissionUseCase,
): ViewModel() {
    private val _loginKakao = MutableLiveData<Unit>()
    val loginKakao: LiveData<Unit>
        get() = _loginKakao

    private val _recommendFriends = MutableLiveData<List<FriendUiModel>>()
    val recommendFriends: LiveData<List<FriendUiModel>>
        get() = _recommendFriends

    private val _isAgreedKakaoFriendsPermission = MutableLiveData<Boolean>(false)
    val isAgreedKakaoFriendsPermission: LiveData<Boolean>
        get() = _isAgreedKakaoFriendsPermission

    fun initAgreedKakaoFriendsPermission() {
        _isAgreedKakaoFriendsPermission.value = getKakaoFriendsPermissionUseCase()
    }

    fun getRecommendedFriends() {
        invalidKakaoToken()

        if (_isAgreedKakaoFriendsPermission.value == true) {
            viewModelScope.launch {
                _recommendFriends.value = getRecommendedFriendsUseCase().map { it.toUiModel() }
            }
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

    /**
     * 카카오에 친구목록 불러오는 권한 있는지 체크하는 함수
     * */
    fun checkKakaoFriendsPermission(context: Context) {
        val scopes = mutableListOf(KAKAO_FRIENDS_PERMISSION_ID)

        UserApiClient.instance.scopes(scopes) { scopeInfo, error->
            if (error != null) {
                Log.e(TAG, "동의 정보 확인 실패", error)
            } else if (scopeInfo != null) {
                Log.i(TAG, "동의 정보 확인 성공\n 현재 가지고 있는 동의 항목 $scopeInfo")

                if (
                    scopeInfo.scopes?.get(0)?.id == KAKAO_FRIENDS_PERMISSION_ID &&
                    scopeInfo.scopes?.get(0)?.agreed == true
                ) {
                    setAgreeKakaoFriendsPermission(true)
                    getRecommendedFriends()
                } else {
                    setAgreeKakaoFriendsPermission(false)
                    requestGetKakaoFriendsPermission(context)
                }
            }
        }
    }

    private fun setAgreeKakaoFriendsPermission(isAgree: Boolean) {
        setKakaoFriendsPermissionUseCase(isAgree)
        _isAgreedKakaoFriendsPermission.value = isAgree
    }

    /* TODO: 삭제 예정 코드
    *   권한 삭제 테스트를 위해 남겨두었습니다. */
    private fun deleteFriendsPermission() {
        val scopes = mutableListOf(KAKAO_FRIENDS_PERMISSION_ID)

        UserApiClient.instance.revokeScopes(scopes) { scopeInfo, error->
            if (error != null) {
                Log.e(TAG, "동의 철회 실패", error)
            }else if (scopeInfo != null) {
                Log.i(TAG, "동의 철회 성공\n 현재 가지고 있는 동의 항목 $scopeInfo")
            }
        }
    }

    /* TODO: context 관련 정리하자 */
    /** kakao에 친구목록 불러오는 권한 요청 */
    private fun requestGetKakaoFriendsPermission(context: Context) {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            } else if (user != null) {
                val scopes = mutableListOf(KAKAO_FRIENDS_PERMISSION_ID)

                if (scopes.isNotEmpty()) {
                    Log.d(TAG, "사용자에게 추가 동의를 받아야 합니다.")

                    //scope 목록을 전달하여 카카오 로그인 요청
                    UserApiClient.instance.loginWithNewScopes(context, scopes) { token, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 추가 동의 실패", error)
                        } else {
                            Log.d(TAG, "allowed scopes: ${token!!.scopes}")
                            if (token.scopes?.contains("friends") == true) {
                                setAgreeKakaoFriendsPermission(true)
                            }

                            // 사용자 정보 재요청
                            UserApiClient.instance.me { user, error ->
                                if (error != null) {
                                    Log.e(TAG, "사용자 정보 요청 실패", error)
                                } else if (user != null) {
                                    Log.i(TAG, "사용자 정보 요청 성공")
                                }
                            }
                        }
                    }
                }
            }
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
            Log.d(TAG, it.toString())
            setKakaoTokenUseCase(
                accessToken = it.accessToken,
                refreshToken = it.refreshToken,
            )
        }
    }

    companion object {
        const val TAG = "RecommendFriendsViewModel"
        const val KAKAO_FRIENDS_PERMISSION_ID = "friends"
    }
}
