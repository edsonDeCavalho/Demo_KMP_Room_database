package ui.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier =  Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onclickToDetails() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

            ) {
            // Image on the left
            KamelImage(
                resource = asyncPainterResource("https://store-images.s-microsoft.com/image/apps.14650.14523499105264405.5af49363-c5aa-48f7-8247-82e253ce4b89.b2a77e40-3316-4086-b910-74eb3db04a4a"),
                contentDescription = "Note Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(18.dp))
                    .clip(
                        RoundedCornerShape(
                            topEnd = 18.dp,
                            topStart = 18.dp,
                            bottomEnd = 18.dp,
                            bottomStart = 18.dp
                        )
                    )

            )
            Text(
                text = note.title,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                fontSize = 16.sp,
                color = Color.Black
            )
            IconButton(onClick = { onDeleteClick() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Red
                )
            }
        }
    }
}
