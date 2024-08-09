package ui.pages.notedetails

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.LocalDatabase
import data.Note
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

/**
 * Page NoteDetails
 */
@Composable
fun NoteDetails(noteId: Long, paddingModifier: Modifier, mainViewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    var note by remember { mutableStateOf<Note?>(null) }
    val noteDao = LocalDatabase.current.noteDao()
    val snackbarHostState = remember { SnackbarHostState() }
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    LaunchedEffect(noteId) {
        note = noteDao.getNoteById(noteId)
        note?.let {
            title = it.title
            text = it.text
            image = it.image
            mainViewModel.updateTitle(it.title)
        }
    }
    fun enregistremmentDeMidifications() {
        note?.let {
            val updatedNote = it.copy(title = title, text = text, image = image)
            scope.launch {
                noteDao.updateNote(updatedNote)
                    .also { snackbarHostState.showSnackbar("Modifications enregistrées!") }

            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 50.dp)
            )
        },
        content = { innerPadding ->
            /**
             * Pour avoir une page scrollable en ajoute lazy Column
             */
            LazyColumn(
                modifier = paddingModifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item{Spacer(modifier = Modifier.height(8.dp))
                KamelImage(
                    resource = asyncPainterResource("https://store-images.s-microsoft.com/image/apps.14650.14523499105264405.5af49363-c5aa-48f7-8247-82e253ce4b89.b2a77e40-3316-4086-b910-74eb3db04a4a"),
                    contentDescription = "Image note",
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(18.dp))
                        .clip(
                            RoundedCornerShape(
                                topEnd = 18.dp,
                                topStart = 18.dp,
                                bottomEnd = 18.dp,
                                bottomStart = 18.dp
                            )
                        )
                )

               Spacer(modifier = Modifier.height(18.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = title,
                            fontSize = 24.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        IconButton(
                            onClick = { /* do something */ },
                            modifier = Modifier.size(56.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = "Edit Note",
                                tint = Color.Black
                            )
                        }
                    }

             Spacer(modifier = Modifier.height(18.dp))
                }

                item{  TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("écrivez votre text svp ...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp, bottomEnd = 8.dp, bottomStart = 8.dp)),
                    maxLines = 10
                ) }

                item{
                     Spacer(modifier = Modifier.height(16.dp))
                     Button(onClick = { enregistremmentDeMidifications() },) {
                        Text("Enregistrer modifications")
                    }
                }
            }
        }
    )
}

