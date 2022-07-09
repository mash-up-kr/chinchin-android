package com.mashup.presenter.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.presenter.ui.main.MainNavBar
import com.mashup.presenter.ui.main.MainNavGraph
import com.mashup.presenter.ui.main.MainNavScreen
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
                            MainNavScreen.Profile,
                            MainNavScreen.Setting,
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
