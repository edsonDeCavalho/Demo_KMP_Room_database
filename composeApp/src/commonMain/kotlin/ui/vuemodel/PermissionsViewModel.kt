package ui.vuemodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.launch

/**
 *
 * PermissionsViewModel
 *
 */
class PermissionsViewModel(
    private val controller: PermissionsController
): ViewModel() {

    var state_GALLERY by mutableStateOf(PermissionState.NotDetermined)
        private set
    var state_STORAGE by mutableStateOf(PermissionState.NotDetermined)
        private set
    var state_WRITE_STORAGE by mutableStateOf(PermissionState.NotDetermined)
        private set

    init {
        viewModelScope.launch {
            state_GALLERY = controller.getPermissionState(Permission.GALLERY)
            state_STORAGE = controller.getPermissionState(Permission.STORAGE)
            state_WRITE_STORAGE = controller.getPermissionState(Permission.WRITE_STORAGE)
        }
    }
    /**
     * state_GALLERY
     */
    fun provideOrRequestGallery() {
        viewModelScope.launch {
            try {
                controller.providePermission(Permission.RECORD_AUDIO)
                state_GALLERY = PermissionState.Granted
            } catch(e: DeniedAlwaysException) {
                state_GALLERY = PermissionState.DeniedAlways
            } catch(e: DeniedException) {
                state_GALLERY = PermissionState.Denied
            } catch(e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }
    /**
     * state_STORAGE
     */
    fun provideOrRequestSTORAGE() {
        viewModelScope.launch {
            try {
                controller.providePermission(Permission.STORAGE)
                state_STORAGE = PermissionState.Granted
            } catch(e: DeniedAlwaysException) {
                state_STORAGE = PermissionState.DeniedAlways
            } catch(e: DeniedException) {
                state_STORAGE = PermissionState.Denied
            } catch(e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * state_WRITE_STORAGE
     */
    fun provideOrRequestWRITE_STORAGE() {
        viewModelScope.launch {
            try {
                controller.providePermission(Permission.WRITE_STORAGE)
                state_WRITE_STORAGE = PermissionState.Granted
            } catch(e: DeniedAlwaysException) {
                state_WRITE_STORAGE = PermissionState.DeniedAlways
            } catch(e: DeniedException) {
                state_WRITE_STORAGE = PermissionState.Denied
            } catch(e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }
}