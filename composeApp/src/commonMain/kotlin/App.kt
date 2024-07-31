import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.room.RoomDatabase
import data.database.Kmp_database
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.pages.mainScreen.MainScreen

/**
 * App entry
 */
@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<Kmp_database>) {
    val database = remember { databaseBuilder.build() }

    MaterialTheme{
        /**
         * Main page
         */
        MainScreen(database)

    }
}

