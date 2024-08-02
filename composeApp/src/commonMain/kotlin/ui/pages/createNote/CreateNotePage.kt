package ui.pages.createNote

import MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.LocalDatabase
import data.Note
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch



@Composable
fun CreateNotePage(paddingModifier: Modifier, mainViewModel : MainViewModel){
    val scope = rememberCoroutineScope()
    val noteDao = LocalDatabase.current.noteDao()
    val snackbarHostState = remember { SnackbarHostState() }
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 50.dp)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = paddingModifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchedEffect(Unit) {
                    mainViewModel.updateTitle("Create a Note")
                }

                fun saveNote() {
                    scope.launch {
                        val newNote = Note(
                            id = 0, // Assuming the ID is auto-generated
                            title = title,
                            text = text,
                            image = selectedImage ?: ""
                        )
                        noteDao.insertNote(newNote).also { snackbarHostState.showSnackbar("Note enregistré!") }
                        //onNoteCreated()
                    }
                }
                Text("Create a new note")
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Titre") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Text") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp),
                    maxLines = 10
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { saveNote() }) {
                    Text("Enregistrer note")
                }
            }
        })
}


