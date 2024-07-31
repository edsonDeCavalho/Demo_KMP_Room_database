package ui.pages.home

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Compposante page Home
 */
@Composable
fun HomeScreen(paddingModifier: Modifier, mainViewModel : MainViewModel) {
    Column(
        modifier = paddingModifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LaunchedEffect(Unit) {
            mainViewModel.updateTitle("Home")
        }
        Text(text = "home", fontSize = 100.sp)
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(onClick = { }) {
            Text("Room")
        }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedButton(onClick = { }) {
            Text("API")
        }
    }
}