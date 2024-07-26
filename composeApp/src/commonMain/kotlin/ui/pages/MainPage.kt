package ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ui.components.NewsBottomNavigationBar
import ui.constants.listmenu


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(){
    Scaffold (
        topBar = {
           TopAppBar(
               title = {
                   Text("KMPSupport")
               }, actions = {
                   IconButton(onClick = {
                        print("Sttings selected")
                   }){
                       Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
                   }
               }
           )
        }, bottomBar = {
            NewsBottomNavigationBar(
                items =listmenu,
                currentRoute = listmenu.get(0).route,
                onItemClick = {

                }
            )
        }
    ){
        Column {
            Text(text ="Hello world")
        }
    }
}