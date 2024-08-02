package ui.pages.apidemo

import MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

/**
 * Page demo de Api
 */
@Composable
fun ApiDemoPage(paddingModifier: Modifier = Modifier.fillMaxSize(),
                navController: NavController,
                mainViewModel : MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Api Demo")
    }
    Scaffold {
      MaterialTheme {
          Column (modifier = paddingModifier){
              Text(
                  text = "Page Api",
                  textAlign = TextAlign.Center,
                  fontSize = 20.sp
              )
          }
      }
    }
}