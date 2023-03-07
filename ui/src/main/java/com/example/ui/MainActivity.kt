package com.example.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coreui.components.Spinner
import com.example.coreui.components.SpinnerOption
import com.example.ui.details.DetailsScreen
import com.example.ui.list.ListScreen
import com.example.ui.list.models.MostPopular
import com.example.ui.list.models.TopRated
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val controller = rememberNavController()
            NavHost(navController = controller, startDestination = "/list") {
                composable("/list") {
                    ListScreen { id ->
                        controller.navigate("/movie/$id")
                    }
                }
                composable("/movie/{id}", arguments = listOf(navArgument("id") {
                    defaultValue = 0
                })) {
                    DetailsScreen(it.arguments?.getInt("id") ?: 0) {
                        controller.popBackStack()
                    }
                }
            }
        }
    }
}

@Composable
fun AppHeader(selected: SpinnerOption, onSortMethodChanged: (method: SpinnerOption) -> Unit) {
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = stringResource(R.string.header_title))
        },
        actions = {
            Spinner(options = arrayOf(MostPopular, TopRated), selectedItem = selected, onItemSelected = onSortMethodChanged)
        }
    )
}