import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import core.db.data.Application
import data.database.Kmp_database
import kotlinx.coroutines.launch
import navigation.NavController
import navigation.PageInterface
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.pages.HomePage
import ui.pages.Page1
import ui.pages.Page2



enum class Kmp_database_scren() {
    Home,
    Page1,
    Page2,
}

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<Kmp_database>) {

    Surface (modifier = Modifier.fillMaxSize()){
        val navController = remember { NavController(PageInterface.HomePage) }
        val curretPage by navController.currentPage.collectAsState()

        HomePage(visible = curretPage == PageInterface.HomePage, navController = navController)
        Page1(databaseBuilder= databaseBuilder, visible = curretPage == PageInterface.Page1, navController = navController)
        Page2(visible = curretPage == PageInterface.Page2, navController = navController)
    }
}

