package ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.room.RoomDatabase
import core.db.data.Application
import data.database.Kmp_database
import kotlinx.coroutines.launch
import navigation.PageInterface

@Composable
fun ApplicationList(
    databaseBuilder: RoomDatabase.Builder<Kmp_database>,
    paddingModifier: Modifier = Modifier.fillMaxSize(),
    navController: NavHostController,
) {
        Column(
            modifier = paddingModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MaterialTheme{
                Text(text = "Page room : Database")

                val database = remember { databaseBuilder.build() }
                val applicationDao = remember { database.applicationDao() }
                val applications by applicationDao.getAllApplications().collectAsState(initial = emptyList())

                val scope = rememberCoroutineScope()

                LaunchedEffect(true) {
                    val applicationList = listOf(
                        Application(nom="NetPlus", description="HelloV1"),
                        Application(nom="WeatherPro", description="ForecastV1"),
                        Application(nom="MusicStream", description="TuneIn"),
                        Application(nom="PhotoEdit", description="SnapFix"),
                        Application(nom="VideoPlay", description="StreamV2"),
                        Application(nom="ChatNow", description="InstantMsg"),
                        Application(nom="GameZone", description="PlayNow"),
                        Application(nom="FinanceMgr", description="BudgetV2"),
                        Application(nom="HealthTrack", description="Wellness"),
                        Application(nom="FitApp", description="WorkoutV1"),
                        Application(nom="CookBook", description="RecipeV1"),
                        Application(nom="ReadIt", description="BookWorm"),
                        Application(nom="ShopEasy", description="DealsV2"),
                        Application(nom="TravelBuddy", description="TripGuide"),
                        Application(nom="StudyMate", description="LearnV1"),
                        Application(nom="NoteTaker", description="MemoV2"),
                        Application(nom="NewsNow", description="Headlines"),
                        Application(nom="TaskMaster", description="ToDoV1"),
                        Application(nom="MindRelax", description="CalmV1"),
                        Application(nom="SportWatch", description="ScoresV2"),
                        Application(nom="LangLearn", description="SpeakV1"),
                        Application(nom="PetCare", description="FurryV1"),
                        Application(nom="FoodOrder", description="EatsV1"),
                        Application(nom="CarAssist", description="DriveV2"),
                        Application(nom="GardenPlan", description="GrowIt"),
                        Application(nom="FashionFit", description="StyleV1"),
                        Application(nom="EventCheck", description="AttendV1"),
                        Application(nom="HouseKeep", description="CleanV2"),
                        Application(nom="BudgetBuddy", description="SaveV1"),
                        Application(nom="SocialBuzz", description="ConnectV1"),
                    )
                    applicationList.forEach {
                        applicationDao.insertApplication(it)
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(applications.size) { index ->
                        val application = applications[index]
                        Text(
                            text = " \uD83D\uDC7E id : "+application.id+" Nom : "+application.nom+" Description "+application.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        applicationDao.deleteApplication(applications[index])
                                    }
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
}