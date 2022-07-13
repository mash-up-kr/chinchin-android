package com.mashup.presenter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
                ItemColumn(viewModel)
            }
        }
    }
}

@Composable
fun ItemColumn(viewModel: MainViewModel) {
    Column {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.addChinChin("mashup") },
        ) {
            Text(text = "친구 추가")
        }
        LazyColumn(
            modifier = Modifier
                .background(color = Color.Gray)
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(viewModel.items.value) { item ->
                Column {
                    Text(text = item.name)
                    Text(text = item.date.toString())
                    Text(text = if (item.isFriend) "친구 추가됨" else "친구 추가 안됨")
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(color = Color.Black, thickness = 1.dp)
                    Row {
                        Button(onClick = { viewModel.updateChinChin(item.uid) }) {
                            Text("친구로 등록")
                        }
                        Button(onClick = { viewModel.deleteChinChin(item.uid) }) {
                            Text("친구 삭제")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChinchinTheme {
        Greeting("Android")
    }
}