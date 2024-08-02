package ui.pages.cali

import MainViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InfoPage(paddingModifier: Modifier,mainViewModel : MainViewModel) {
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Informations")
    }
   // mainViewModel.updateTitle("Home")
    Box(
        modifier = paddingModifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart,
    ) {
       // Text(text="InfoPage", fontSize = 70.sp , modifier = Modifier.align(Alignment.Center))
        Scaffold {
            MaterialTheme {
            LazyColumn( modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Navigation",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Elevated in a way that it's imcompresible for the human eye giving a force undestructable.",
                            modifier = Modifier
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Navigation",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Elevated in a way that it's imcompresible for the human eye giving a force undestructable.",
                            modifier = Modifier
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Navigation",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Elevated in a way that it's imcompresible for the human eye giving a force undestructable.",
                            modifier = Modifier
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                item {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Navigation",
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Elevated in a way that it's imcompresible for the human eye giving a force undestructable.",
                            modifier = Modifier
                                .padding(10.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
            }
        }
    }
}