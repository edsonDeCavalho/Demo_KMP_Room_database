package ui.pages.options

import MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

/**
 * Page Options
 */
@Composable
fun Options(paddingModifier: Modifier, mainViewModel : MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Options")
    }
    Box(
        modifier = paddingModifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(text="Options", fontSize = 100.sp , modifier = Modifier.align(Alignment.Center))
        Row {
            ImageSelector()
        }
    }
}



@Composable
fun ImageSelector() {
    // List of drawable resources
    val images = listOf(
        "https://www.barnorama.com/wp-content/uploads/2023/10/bing-ai13.png",
        "https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp",
        "https://img.freepik.com/premium-photo/cat-wearing-sunglasses-suit-with-tie-generative-ai-image_991150-2685.jpg",
        "https://img.freepik.com/premium-photo/cat-wearing-sunglasses-suit-with-tie-generative-ai-image_991150-2685.jpg",
        "https://img.freepik.com/premium-photo/cat-wearing-sunglasses-suit-with-tie-generative-ai-image_991150-2685.jpg",
        "https://img.freepik.com/photos-premium/pussy-cat_861875-9927.jpg"
        // Add more images as needed
    )

    // State to hold the selected image resource ID
    var selectedImage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display selected image

        selectedImage?.let {
            KamelImage(
                resource = asyncPainterResource(it),
                contentDescription = "Image note",
                modifier = Modifier
                    .size(200.dp).padding(16.dp)
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
        Spacer(modifier = Modifier.height(16.dp))

        Text("Select an Image:")

        Spacer(modifier = Modifier.height(16.dp))

        // List of images to select from
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize()
        ) {
            items(images.size) { index ->
                ImageItem(images[index]) {
                    selectedImage = images[index]
                }
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
            .size(100.dp)
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