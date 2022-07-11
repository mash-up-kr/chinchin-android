package com.mashup.presenter.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.mashup.presenter.ui.theme.Black
import com.mashup.presenter.ui.theme.White

@Composable
fun MainNavBar(
    screens: List<MainNavScreen>,
    currentDestination: MainNavScreen,
    onTabSelected: (MainNavScreen) -> Unit,
) {
    BottomNavigation(backgroundColor = White) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                onTabSelected = {
                    onTabSelected(screen)
                }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainNavScreen,
    currentDestination: MainNavScreen,
    onTabSelected: () -> Unit,
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.iconResId),
                contentDescription = "Navigation Icon"
            )
        },
        selectedContentColor = Black,
        /* TODO: #d9d9d9가 이름이 나온다면 Color에 고정값을 정의하고 고정값을 사용하도록 하자 */
        unselectedContentColor = Color(0xFFD9D9D9),
        selected = currentDestination == screen,
        onClick = { onTabSelected() }
    )
}
