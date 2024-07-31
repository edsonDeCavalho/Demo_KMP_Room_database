import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import core.LocalDatabase
import data.Note
import data.database.Kmp_database
import kotlinx.coroutines.launch
import ui.components.items.NoteItem

/**
 * Page list de notes
 */
@Composable
fun NotesListePage(
    database: Kmp_database,
    paddingModifier: Modifier = Modifier.fillMaxSize(),
    navController: NavController,
    mainViewModel : MainViewModel
) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Demo Room")
    }

    Box(modifier = paddingModifier) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color.Gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MaterialTheme {
                Text(text = "Page room : Database")


                val database_test = LocalDatabase.current
                val noteDao = database_test.noteDao()
                val notesList by noteDao.getAllNotes().collectAsState(initial = emptyList())
                val scope = rememberCoroutineScope()


                LaunchedEffect(true) {
                    val notestToInsert = listOf(
                        Note(title="NetPlus", text="HelloV1",image =""),
                        Note(title="WeatherPro", text="ForecastV1",image =""),
                        Note(title="MusicStream", text="TuneIn",image =""),
                 /*     Note(title="PhotoEdit", text="SnapFix",image =""),
                        Note(title="VideoPlay", text="StreamV2",image =""),
                        Note(title="ChatNow", text="InstantMsg",image =""),
                        Note(title="GameZone", text="PlayNow",image =""),
                        Note(title="FinanceMgr", text="BudgetV2",image =""),
                        Note(title="HealthTrack", text="Wellness",image =""),
                        Note(title="FitApp", text="WorkoutV1",image =""),
                        Note(title="CookBook", text="RecipeV1",image =""),
                        Note(title="ReadIt", text="BookWorm",image =""),
                        Note(title="ShopEasy", text="DealsV2",image =""),
                        Note(title="TravelBuddy", text="TripGuide",image =""),
                        Note(title="StudyMate", text="LearnV1",image =""),
                        Note(title="NoteTaker", text="MemoV2",image =""),
                        Note(title="NewsNow", text="Headlines",image =""),
                        Note(title="TaskMaster", text="ToDoV1",image =""),
                        Note(title="MindRelax", text="CalmV1",image =""),*/
                    )
                    notestToInsert.forEach {
                        noteDao.insertNote(it)
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(notesList.size) { index ->
                        val note = notesList[index]
                        NoteItem(note,
                            onDeleteClick = {
                            scope.launch {
                                noteDao.deleteNote(note)
                            }
                        }, onclickToDetails = { navController.navigate("notedetails/${note.id}") })
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { println("Add data") },
            containerColor = Color.Blue,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp) // Adjust the padding as needed
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Ajouter une note", // Corrected from 'contenttext' to 'contentDescription'
                modifier = Modifier.size(24.dp), // Adjust the size as needed
                tint = Color.White // Adjust the color as needed
            )
        }
    }
}


