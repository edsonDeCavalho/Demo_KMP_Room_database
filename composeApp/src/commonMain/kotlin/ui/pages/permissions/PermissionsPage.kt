package ui.pages.permissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ui.vuemodel.PermissionsViewModel
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

/**
 * Permissions Page
 * @author Edson De Carvalho
 */
@Composable
fun PermissionsPage(){
    val factory = rememberPermissionsControllerFactory()
    val controller = remember(factory) {
        factory.createPermissionsController()
    }
    BindEffect(controller)
    val viewModel = viewModel {
        PermissionsViewModel(controller)
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Demande et verification de permissions")
        when(viewModel.state_GALLERY) {
            PermissionState.Granted -> {
                Text("Autorisation d'enregistrement gallerie accordée!")
            }
            PermissionState.DeniedAlways -> {
                Text("L'autorisation a été définitivement refusée..")
                Button(onClick = {
                    controller.openAppSettings()
                }) {
                    Text("Ouvrir les paramètres de l'application")
                }
            }
            else -> {
                Button(
                    onClick = {
                        viewModel.provideOrRequestGallery()
                    }
                ) {
                    Text("Demander la permission")
                }
            }
        }
    }
}