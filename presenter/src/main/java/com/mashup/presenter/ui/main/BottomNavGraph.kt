package com.mashup.presenter.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.findNavController
import com.mashup.presenter.ui.main.screens.HomeScreen
import com.mashup.presenter.ui.main.screens.ProfileScreen
import com.mashup.presenter.ui.main.screens.SettingScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    )
    {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Setting.route){
            SettingScreen()
        }
    }

}