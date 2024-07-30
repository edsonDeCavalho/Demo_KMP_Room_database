import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _appBarTitle = MutableStateFlow("Home")
    val appBarTitle: StateFlow<String> = _appBarTitle

    fun updateTitle(newTitle: String) {
        _appBarTitle.value = newTitle
    }
}