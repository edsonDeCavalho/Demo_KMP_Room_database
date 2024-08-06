package ui.pages.apidemo

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import core.managers.api.APIManager
import kotlinx.coroutines.launch

/**
 * Page demo de Api
 * @author Edson De Carvalho Pedro
 */
@Composable
fun ApiDemoPage(paddingModifier: Modifier = Modifier.fillMaxSize(),
                navController: NavController,
                mainViewModel : MainViewModel) {

    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Api Demo")
    }
    //var scope = rememberCoroutineScope()
    ///var apim = APIManager()
    //scope.launch {
      //  print(apim.getAllNotes())
    //}


    Scaffold {
      MaterialTheme {
          Column (modifier = paddingModifier
              .fillMaxSize()
              .padding(10.dp),
              verticalArrangement = Arrangement.Center,
              horizontalAlignment = Alignment.CenterHorizontally){
              Text(
                  text = "Page Api",
                  textAlign = TextAlign.Center,
                  fontSize = 20.sp
              )
          }
      }
    }
}