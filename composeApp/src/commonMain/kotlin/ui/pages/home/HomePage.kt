package ui.pages.home

import MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kmp_database.composeapp.generated.resources.Res
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

/**
 * Compposante page Home
 */
@Composable
fun HomeScreen(paddingModifier: Modifier,navController: NavHostController,mainViewModel : MainViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("KMPSupport")
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 50.dp)
            )
        },
        content = { innerPadding ->

            Column(
                modifier = paddingModifier
                    .fillMaxSize()
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "KMPSupport", fontSize = 50.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Ci-dessus, vous trouverez différentes fonctionnalités en Kotlin multiplateforme :",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                ElevatedButton(onClick = { navController.navigate("room") }) {
                    Text("Room")
                }
                Spacer(modifier = Modifier.height(16.dp))
                ElevatedButton(onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Functionalité pas disponibe pour le moment")
                    }
                }) {

                    Text("API")
                }
            }
        })
}