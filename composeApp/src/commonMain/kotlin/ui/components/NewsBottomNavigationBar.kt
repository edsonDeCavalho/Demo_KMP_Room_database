package ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.BottomNavigationItem
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsBottomNavigationBar(
    items: List<BottomNavigationItem>,
    currentRoute: String?,
    onItemClick: (BottomNavigationItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach {
           NavigationBarItem(
               selected = currentRoute == it.route,
               onClick = {
                   onItemClick(it)
               },
               icon = {
                   Icon(
                       imageVector = Icons.Filled.Remove,
                       contentDescription = "Settings"
                   )
               },
               label = {
                   Text(text = stringResource(it.title))
               }
           )
        }
    }
}
