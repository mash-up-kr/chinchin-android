package com.mashup.presenter.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: 카카오 Init, Splash로 옮기기
        initViewModel()

        setContent {
            ChinchinTheme {
                LoginButton("친친 로그인하기", viewModel)
            }
        }
    }

    private fun initViewModel() {
        viewModel.errorMessage.observe(this) {
            Log.e("LoginActivity 로그인할때 에러났대요", it)
        }
    }
}

@Composable
private fun LoginButton(name: String, viewModel: LoginViewModel) {
    val context = LocalContext.current

    Button(onClick = { kakaoLogin(context, viewModel) }) {
        Text(text = name)
    }
}

private fun kakaoLogin(context: Context, viewModel: LoginViewModel) {
    viewModel.kakaoLogin(context)
}