package ui.pages.Info

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun InfoPage(paddingModifier: Modifier, mainViewModel: MainViewModel) {
    mainViewModel.updateTitle("Informations")
    Box(
        modifier = paddingModifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Text(text="InfoPage", fontSize = 70.sp , modifier = Modifier.align(Alignment.Center))
    }
}