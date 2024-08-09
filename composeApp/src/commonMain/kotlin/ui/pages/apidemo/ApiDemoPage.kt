package ui.pages.apidemo

import MainViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import core.LocalDatabase
import core.managers.api.NoteApiManager
import data.Note
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.launch
import ui.components.items.NoteItem


/**
 * Page demo de Api : Etch une api
 *
 * Cette adresse : http://93.90.200.94:8013/notes/allnotes correspond a l'adresse d'un de mes serveurs.
 * Il foudra deployer la mini API Ktor sur un serveur. Pour pouvoir tester cette partie.
 * @author Edson De Carvalho Pedro
 */
@Composable
fun ApiDemoPage(paddingModifier: Modifier = Modifier.fillMaxSize(),
                navController: NavController,
                mainViewModel : MainViewModel) {

    LaunchedEffect(Unit) {
        mainViewModel.updateTitle("Api Demo")
    }
    /**
     * Dans une premier partie en fais un fetch su rune api publique
     * (api pour recuperer des curiosités sur les chats)
     */
    val scope = rememberCoroutineScope()

    //Variable API
    var responseApichats by mutableStateOf<String?>(null)
    var listOfNotes by remember { mutableStateOf<List<Note>?>(null) }
    var apiManager = NoteApiManager()

    //Variable database
    val database = LocalDatabase.current
    val noteDao = database.noteDao()
    //SnackBaar
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold ( snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(bottom = 50.dp)
        )
    })
    {
      MaterialTheme {
          LazyColumn (modifier = paddingModifier
              .fillMaxSize()
              .padding(10.dp),
              verticalArrangement = Arrangement.Top,){
              item {Spacer(modifier = Modifier.height(10.dp)) }
              item {Text(
                  text = "Api publique",
                  textAlign = TextAlign.Center,
                  fontSize = 25.sp,
                  fontWeight = FontWeight.W700
              ) }
              item {Spacer(modifier = Modifier.height(5.dp)) }
              item {Text(
                  text = "Fetch curiosité sur les chats :",
                  textAlign = TextAlign.Left,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.W500
              ) }
              item {Spacer(modifier = Modifier.height(5.dp)) }
              item{
                  ElevatedButton(onClick = {scope.launch {
                      responseApichats = fetchApiChats()
                  }}) {
                      Text("Fetch")
                  }
              }

              if (responseApichats != null) {
                  item{  Text("Response Http : $responseApichats") }
                 scope.launch {
                     snackbarHostState.showSnackbar("Fetch Api chats bien executée \uD83D\uDE03\n")
                 }
              } else {
                  item{ Text("") }
              }
              item {
                  Spacer(modifier = Modifier.height(height = 15.dp))
                  Text(
                  text = "Api notes (serveur Ktor)",
                  textAlign = TextAlign.Center,
                  fontSize = 25.sp,
                  fontWeight = FontWeight.W700
              ) }
              item {Spacer(modifier = Modifier.height(5.dp)) }
              item {Text(
                  text = "Fetch liste de notes :",
                  textAlign = TextAlign.Left,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.W500
              ) }
              item {
                  ElevatedButton(onClick = {
                      scope.launch {
                          listOfNotes = apiManager.fetchNotes().apply {
                              if(listOfNotes?.isNotEmpty() == true){
                                  snackbarHostState.showSnackbar("Fetch Api Ktor bien executée \uD83D\uDE03\n")
                              }
                          }
                      }
                  }) {
                      Text("Fetch")
                  }
              }
              if (listOfNotes?.isNotEmpty() == true) {

                  listOfNotes?.forEach {
                      //Insertion des notes récupées dans la base de données
                      scope.launch {
                          noteDao.insertNote(it)
                      }
                      item{
                          Spacer(modifier =Modifier.height(10.dp))
                          NoteItem(note = it,onclickToDetails = {},
                              onDeleteClick = {
                                  scope.launch{
                                     if(apiManager.deleteNote(it.id)){
                                         snackbarHostState.showSnackbar("Note supprimée! \uD83D\uDE0B\n")
                                     }else{
                                         snackbarHostState.showSnackbar("Erreur lors de la suppression de la note \uD83E\uDD2F")
                                     }
                                      noteDao.deleteNote(it)
                                      listOfNotes = apiManager.fetchNotes()
                                  }
                              }
                          )
                          Spacer(modifier =Modifier.height(10.dp))
                      }
                  }
              } else {
                 item {  Text("")}
              }
              item {
                  Spacer(modifier =Modifier.height(5.dp))
                  Text(
                      text = "Inserer une note :",
                      textAlign = TextAlign.Left,
                      fontSize = 20.sp,
                      fontWeight = FontWeight.W500
                  )
              }
              item {
                  ElevatedButton(onClick = {
                      scope.launch {
                          apiManager.addNote(Note(title="Note insert \uD83D\uDE1C\n",text="note insert",image="")).apply {
                              listOfNotes = apiManager.fetchNotes()
                              snackbarHostState.showSnackbar("Note ajoutée correctemment ! \uD83E\uDD73\n")
                          }
                      }
                  }) {
                      Text("Insert")
                  }
              }
          }
      }
    }
}

/**
 * Fetch les noetes presetes dans le serveur Ktor
 * @return [String]
 */
suspend fun fetchNotes(): String? {
    val client = HttpClient()
    var responseData by mutableStateOf<String?>(null)
    try {
        val response: HttpResponse = client.request("http://93.90.200.94:8013/notes/allnotes") {
            method = HttpMethod.Get
        }
        val body: String = response.bodyAsText()
        responseData = body

    } catch (e: Exception) {
        responseData = "Error: ${e.message}"
    } finally {
        client.close()
    }
    return responseData
}

suspend fun fetchApiChats(): String? {
    val client = HttpClient()
    var responseData by mutableStateOf<String?>(null)
    try {
        val response: HttpResponse = client.request("https://catfact.ninja/fact") {
            method = HttpMethod.Get
        }
        val body: String = response.bodyAsText()
        responseData = body

    } catch (e: Exception) {
        responseData = "Error: ${e.message}"
    } finally {
        client.close()
    }
    return responseData
}