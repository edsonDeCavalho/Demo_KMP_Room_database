package ui.pages.notedetails

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

/**
 * Page NoteDetails
 */
@Composable
fun NoteDetails(noteId: Long, paddingModifier: Modifier, mainViewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    var note by remember { mutableStateOf<Note?>(null) }
    val noteDao = LocalDatabase.current.noteDao()

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(true) }
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
    Column(
        modifier = paddingModifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        KamelImage(
            resource = asyncPainterResource("https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp"),
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
                .padding(16.dp) // Ensure there's enough space around the row
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f) // Take up remaining horizontal space
            )
            Spacer(modifier = Modifier.width(4.dp)) // Add a small spacer if needed
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier
                    .size(56.dp) // Adjust size as needed
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit Note",
                    tint = Color.Black // Set icon color to black for better visibility
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("écrivez votre text svp ...") },
            modifier = Modifier.fillMaxWidth().background(Color.Transparent)
                .clip(RoundedCornerShape(topEnd = 8.dp , topStart = 8.dp, bottomEnd = 8.dp, bottomStart = 8.dp)),
            maxLines = 10
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }) {
            Text("Enregistrer modifications")
        }
    }
}