package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Note
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

/**
 * Item d'une Note
 */
@Composable
fun NoteItem(
    note: Note,
    onDeleteClick: () -> Unit,
    onclickToDetails: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { onclickToDetails() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        // Image on the left
        KamelImage(
            resource = asyncPainterResource("https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp"),
            contentDescription = "Note Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(topEnd = 18.dp , topStart = 18.dp, bottomEnd = 18.dp, bottomStart = 18.dp))

        )
        Text(
            text = note.title,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            fontSize = 16.sp,
            color = Color.Black
        )

        // Delete button on the right
        IconButton(onClick = {onDeleteClick()}) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Note",
                tint = Color.Red
            )
        }
    }
}
