package ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import navigation.NavController
import navigation.PageInterface

@Composable
fun HomePage(
    modifier: Modifier = Modifier.fillMaxSize(),
    navController: NavController,
    visible : Boolean
){
    if (visible){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text= "Home page")
            Button(
                onClick = {navController.navigateTo(PageInterface.Page1)}
            ){
                Text("Aller la page 1")
            }
        }
    }
}