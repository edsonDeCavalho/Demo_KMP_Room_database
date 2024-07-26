package ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        route="home",
        label="Home",
        icon= Icons.Rounded.Home
    )
    object Tasks: BottomBarScreen(
        route="room",
        label="Room",
        icon= Icons.Rounded.Android
    )
    object Options: BottomBarScreen(
        route="options",
        label="Options",
        icon= Icons.Rounded.Settings
    )
}