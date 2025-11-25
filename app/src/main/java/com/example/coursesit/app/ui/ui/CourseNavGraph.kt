package com.example.coursesit.app.ui.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coursesit.app.ui.screen.AuthorizationPage
import com.example.coursesit.app.ui.screen.HomeBodyPage
import com.example.coursesit.app.ui.screen.HomePage
import com.example.coursesit.app.viewModel.MainPageViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel


@Serializable
object HomePageDirection

@Serializable
object AuthorizationPageDirection

@Serializable
object FavoriteCourseDirection

@Serializable
object AccountPageDirection


@Composable
fun CourseNavGraph(
   navController: NavHostController,
   innerPadding: PaddingValues
){


    NavHost(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        navController = navController,
        startDestination = AuthorizationPageDirection
    ){
        composable<AuthorizationPageDirection>{
            AuthorizationPage(
            onEnterClick = { if(it.isSuccess) navController.navigate(HomePageDirection)}
            )
        }

        composable<HomePageDirection>(
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            HomePage()
        }

        composable <FavoriteCourseDirection>{


            Text(modifier =  Modifier.fillMaxSize(), text = "Favorite", textAlign = TextAlign.Center)
        }

        composable <AccountPageDirection>{
            Text(modifier =  Modifier.fillMaxSize(), text = "Account", textAlign = TextAlign.Center)
        }
    }
}