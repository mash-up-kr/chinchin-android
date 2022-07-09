package com.mashup.presenter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.presenter.main.model.Friend
import com.mashup.presenter.main.model.FriendGroupUiModel
import com.mashup.presenter.ui.main.MainNavBar
import com.mashup.presenter.ui.main.MainNavGraph
import com.mashup.presenter.ui.main.MainNavScreen
import com.mashup.presenter.ui.main.home.FriendsGroupList
import com.mashup.presenter.ui.main.home.HomeHeader
import com.mashup.presenter.ui.theme.ChinchinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChinchinTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(
                        listOf(
                            MainNavScreen.Home,
                            MainNavScreen.RecommendFriends,
                            MainNavScreen.More,
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(screens: List<MainNavScreen> = listOf()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = MainNavScreen.fromRoute(navBackStackEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            MainNavBar(screens = screens, currentDestination = currentDestination) { screen ->
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun HomeScreen() {
    HomeHeader()
    ConstraintLayout(modifier = Modifier.background(Color.Cyan)) {
        val dummyGroup = FriendGroupUiModel(
            name = "매쉬업 사람들",
            friends = listOf(
                Friend("히지니", "https://picsum.photos/200"),
                Friend("혜찌니", "https://picsum.photos/200"),
                Friend("경무", "https://picsum.photos/200"),
                Friend("히지니", "https://picsum.photos/200"),
                Friend("혜찌니", "https://picsum.photos/200"),
                Friend("경무", "https://picsum.photos/200"),
                Friend("히지니", "https://picsum.photos/200"),
                Friend("혜찌니", "https://picsum.photos/200"),
                Friend("경무", "https://picsum.photos/200")
            )
        )
        val groups = mutableListOf<FriendGroupUiModel>()
        repeat(20) {
            groups.add(dummyGroup)
        }

        Column {
            FriendsGroupList(groups)
        }
    }
}

@Composable
fun RecommendFriendsScreen() {
    ConstraintLayout(modifier = Modifier.background(Color.Cyan)) {

        val text = createRef()
        Text("RecommendFriends", Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun MoreScreen() {
    ConstraintLayout(modifier = Modifier.background(Color.Green)) {

        val text = createRef()
        Text("More", Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
@Preview
fun PreviewMain() {
    ChinchinTheme {
        Surface(color = MaterialTheme.colors.background) {
            MainScreen(
                listOf(
                    MainNavScreen.Home,
                    MainNavScreen.RecommendFriends,
                    MainNavScreen.More,
                )
            )
        }
    }
}
