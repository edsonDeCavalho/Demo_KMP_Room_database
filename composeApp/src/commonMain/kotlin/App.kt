import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<Kmp_database>) {

    MaterialTheme {

        val database = remember { databaseBuilder.build() }
        val applicationDao = remember { database.applicationDao() }
        val applications by applicationDao.getAllApplications().collectAsState(initial = emptyList())

        val scope = rememberCoroutineScope()

        LaunchedEffect(true) {
            val applicationList = listOf(
                Application(nom="NetPlus",description="HelloV1"),
                Application(nom="NetPlus",description="HelloV1"),
                Application(nom="NetPlus",description="HelloV1"),
                Application(nom="NetPlus",description="HelloV1"),
                Application(nom="NetPlus",description="HelloV1"),
            )
            applicationList.forEach {
                applicationDao.insertApplication(it)
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(applications.size) { index ->
                val application = applications[index]
                Text("$application.nom",
                modifier = Modifier
                        .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            applicationDao.deleteApplication(applications[index])
                        }
                    }
                    .padding(16.dp))
            }
        }
    }
}
