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

/**
 * Page 2
 */
@Composable
fun Page2(
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
            Text(text= "Page 2")
            Button(
                onClick = {navController.navigateTo(PageInterface.HomePage) }
            ){
                Text("Aller a home ")
            }
        }
    }
}