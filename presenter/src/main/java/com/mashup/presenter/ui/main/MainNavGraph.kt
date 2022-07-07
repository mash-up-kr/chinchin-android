package com.mashup.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.presenter.ui.main.screens.HomeScreen
import com.mashup.presenter.ui.main.screens.ProfileScreen
import com.mashup.presenter.ui.main.screens.SettingScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainNavScreen.Home.route,
    ) {
        composable(route = MainNavScreen.Home.route) {
            HomeScreen()
        }
        composable(route = MainNavScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = MainNavScreen.Setting.route) {
            SettingScreen()
        }
    }
}
