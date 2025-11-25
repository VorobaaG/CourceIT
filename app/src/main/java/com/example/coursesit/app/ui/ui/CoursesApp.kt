package com.example.coursesit.app.ui.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.coursesit.app.ui.theme.CoursesITTheme
import com.example.coursesit.app.ui.theme.Dark
import com.example.coursesit.app.ui.theme.White





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

                if(currentRoute != AuthorizationPageDirection::class.java.name){
                    CourseBottomAppBar(
                        currentBottomState = currentBottomPage,
                        onClick = { if (it != currentBottomPage){
                            currentBottomPage = it
                            when(it) {
                            BottomNavigationState.HOME -> navController.navigate(HomePageDirection){
                                popUpTo(0) { inclusive = true }
                            }
                            BottomNavigationState.FAVORITE -> navController.navigate(FavoriteCourseDirection){
                                popUpTo(0) { inclusive = true }
                            }
                            BottomNavigationState.ACCOUNT -> navController.navigate(AccountPageDirection){
                                popUpTo(0) { inclusive = true }
                            }
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
            BackHandler {
                if (navController.previousBackStackEntry != null) {
                    if(navController.previousBackStackEntry?.destination?.route == HomePageDirection::class.java.name) currentBottomPage = BottomNavigationState.HOME
                    else if(navController.previousBackStackEntry?.destination?.route == FavoriteCourseDirection::class.java.name) currentBottomPage = BottomNavigationState.FAVORITE
                    else if(navController.previousBackStackEntry?.destination?.route == AccountPageDirection::class.java.name) currentBottomPage = BottomNavigationState.ACCOUNT
                    navController.popBackStack()

                }
            }
        }
    }
}