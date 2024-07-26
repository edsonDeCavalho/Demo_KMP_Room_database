package ui.constants

import androidx.compose.runtime.Composable
import data.BottomNavigationItem
import kmp_database.composeapp.generated.resources.Res
import kmp_database.composeapp.generated.resources.app_name
import kmp_database.composeapp.generated.resources.compose_multiplatform
import kmp_database.composeapp.generated.resources.home

val listmenu = listOf(
    BottomNavigationItem(
        icon = Res.drawable.compose_multiplatform ,
        title = Res.string.app_name,
        route = "home"
        ),
    BottomNavigationItem(
        icon = Res.drawable.compose_multiplatform ,
        title = Res.string.app_name,
        route = "Page1",
    ),
    BottomNavigationItem(
        icon = Res.drawable.compose_multiplatform ,
        title = Res.string.app_name,
        route = "Page2",
    )
)