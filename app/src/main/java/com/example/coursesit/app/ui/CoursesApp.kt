package com.example.coursesit.app.ui

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coursesit.app.screen.AuthorizationPage

import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.ui.theme.CoursesITTheme
import com.example.coursesit.ui.theme.Dark
import com.example.coursesit.ui.theme.White





@Composable
fun CoursesApp(
    navController: NavHostController = rememberNavController()
)
{
    var currentBottomPage by remember {mutableStateOf(BottomNavigationState.HOME)}
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CoursesITTheme {
        Scaffold(
            bottomBar = {

                if((currentRoute != "com.example.coursesit.app.ui.AuthorizationPageDirection") &&
                    (currentRoute!=null)){
                    CourseBottomAppBar(
                        currentBottomState = currentBottomPage,
                        onClick = { if (it != currentBottomPage){
                            currentBottomPage = it
                            when(it) {
                            BottomNavigationState.HOME -> navController.navigate(HomePageDirection)
                            BottomNavigationState.FAVORITE -> navController.navigate(FavoriteCourseDirection)
                            BottomNavigationState.ACCOUNT -> navController.navigate(AccountPageDirection)
                        }
                            }
                        }
                    )
                }
            },

            modifier = Modifier.fillMaxSize(),
            containerColor = Dark,
            contentColor = White
        ) { innerPadding ->
            CourseNavGraph(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
}