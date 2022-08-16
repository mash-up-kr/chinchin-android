package com.mashup.chinchin.presenter.login

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kakao.sdk.user.UserApiClient
import com.mashup.chinchin.presenter.R
import com.mashup.chinchin.presenter.ui.login.IntroductionImage
import com.mashup.chinchin.presenter.ui.login.IntroductionText
import com.mashup.chinchin.presenter.ui.login.KakaoLoginButton
import com.mashup.chinchin.presenter.ui.theme.ChinchinTheme
import com.mashup.chinchin.presenter.ui.theme.Secondary_1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        setContent {
            ChinchinTheme {
                LoginScreen {
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
private fun LoginScreen(kakaoLogin: () -> Unit = {}) {
    Box(
        modifier = Modifier.background(Secondary_1)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(vertical = 153.dp),
        ) {
            Image(painter = painterResource(id = R.drawable.login_logo), contentDescription = "")
            IntroductionText()
            Spacer(modifier = Modifier.height(34.dp))
            IntroductionImage()
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 32.dp)
        ) {
            KakaoLoginButton {
                kakaoLogin()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    ChinchinTheme {
        LoginScreen()
    }
}
