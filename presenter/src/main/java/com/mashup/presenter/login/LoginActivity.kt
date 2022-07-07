package com.mashup.presenter.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.kakao.sdk.user.UserApiClient
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        setContent {
            ChinchinTheme {
                LoginButton("친친 로그인하기") {
                    kakaoLogin()
                }
            }
        }
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(this) {
            Log.e("LoginActivity 로그인할때 에러났대요", it)
        }
    }

    private fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                viewModel.handleKakaoCallback(token, error)
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                viewModel.handleKakaoCallback(token, error)
            }
        }
    }
}

@Composable
private fun LoginButton(name: String, kakaoLogin: () -> Unit) {
    Button(onClick = { kakaoLogin() }) {
        Text(text = name)
    }
}