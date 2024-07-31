package ui.pages.mainScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import data.database.Kmp_database
import ui.components.bottomBar.AppBottomBar
import ui.components.topBar.CustomTopBar
import ui.navigation.BottomNavigationGraph

/**
 * MainScreen page ellement compose prencipal
 * @author Edson De Carvalho
 */
@Composable
fun MainScreen(database : Kmp_database) {
    val navController = rememberNavController()
    val mainViewModel = ViewModelProvider.mainViewModel
    Scaffold(
        topBar = { CustomTopBar(mainViewModel,navController) },
        bottomBar = { AppBottomBar(navController = navController) },
    )
    {paddingValues->
        BottomNavigationGraph(
            navController = navController,
            paddingModifier = Modifier.padding(paddingValues) ,
            database = database,
            mainViewModel = mainViewModel
        )
    }
}