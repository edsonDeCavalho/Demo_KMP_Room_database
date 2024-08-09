import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Vuemodel custom
 */
class MainViewModel {
    private val _appBarTitle = MutableStateFlow("Home")
    val appBarTitle: StateFlow<String> = _appBarTitle

    fun updateTitle(newTitle: String) {
        _appBarTitle.value = newTitle
    }
}
object ViewModelProvider {
    val mainViewModel = MainViewModel()
}