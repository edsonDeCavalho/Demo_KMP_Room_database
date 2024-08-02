package ui.components.topBar

import MainViewModel
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * Custom bar TopAppBar. Aide preincipalmment a changer le titre et les actions
 * presentes sur la bare de navigation en cours de execution
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(mainViewModel: MainViewModel,navController: NavHostController) {
    //Recuperation du titre a afficher
    val title by mainViewModel.appBarTitle.collectAsState()

    TopAppBar(
        title = { Text(title) } ,
        actions = {
            IconButton(onClick = { navController.navigate("info") }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "More Icon",
                    tint = Color.White, // Set the icon color
                    modifier = Modifier.size(34.dp) // Set the icon size
                )
            }
            IconButton(onClick = { navController.navigate("info") }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Paramétres",
                    tint = Color.White, // Set the icon color
                    modifier = Modifier.size(34.dp) // Set the icon size
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        ),
    )
}