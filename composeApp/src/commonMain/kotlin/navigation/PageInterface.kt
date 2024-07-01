package navigation

sealed interface PageInterface {
    data object HomePage : PageInterface
    data object Page1 : PageInterface
    data object Page2 : PageInterface
}