package ui.components.imageSelector

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun ImageSelector(images : List<String>,onImageSelected: (String) -> Unit) {
    // List of drawable resources

    // State to hold the selected image resource ID
    var selectedImage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display selected image

        selectedImage?.let {
            KamelImage(
                resource = asyncPainterResource(it),
                contentDescription = "Image note",
                modifier = Modifier
                    .size(60.dp).padding(16.dp)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 18.dp,
                            topStart = 18.dp,
                            bottomEnd = 18.dp,
                            bottomStart = 18.dp
                        )
                    )
            )
        }
        Spacer(modifier = Modifier.height(3.dp))

        Text("Select an Image:")

        Spacer(modifier = Modifier.height(3.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3 columns
            modifier = Modifier.padding(16.dp)
        ) {
            items(images.size) { index ->
                KamelImage(
                    resource = asyncPainterResource(index),
                    contentDescription = "Image note",
                    modifier = Modifier
                        .size(60.dp).padding(1.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 18.dp,
                                topStart = 18.dp,
                                bottomEnd = 18.dp,
                                bottomStart = 18.dp
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun ImageItem(image :String, onClick: () -> Unit) {
    KamelImage(
        resource = asyncPainterResource(image),
        contentDescription = "Image note",
        modifier = Modifier
            .size(30.dp)
            .padding(8.dp)
            .clip(
                RoundedCornerShape(
                    topEnd = 18.dp,
                    topStart = 18.dp,
                    bottomEnd = 18.dp,
                    bottomStart = 18.dp
                )
            ).clickable(onClick = onClick)
    )
}