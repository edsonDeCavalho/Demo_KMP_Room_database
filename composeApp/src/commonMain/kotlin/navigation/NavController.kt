package navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Nav controller
 */
class NavController(pageInitial : PageInterface) {
    private val _currentPage = MutableStateFlow(pageInitial)
    val currentPage = _currentPage.asStateFlow()
    private val backStack: MutableList<PageInterface> = mutableListOf()

    /**
     * Navigation vers une page
     */
    fun navigateTo(page : PageInterface){
        backStack.add(_currentPage.value)
        _currentPage.update { page }
    }

    /**
     * Navigation en arrier
     */
    fun navigateBack(){
        if(backStack.isNotEmpty()){
            _currentPage.update {backStack.last()}

            backStack.removeLast()
        }
    }


}