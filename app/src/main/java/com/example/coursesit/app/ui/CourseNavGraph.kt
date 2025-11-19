package com.example.coursesit.app.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coursesit.app.screen.AuthorizationPage
import com.example.coursesit.app.screen.HomePage
import com.example.coursesit.app.viewModel.AuthorizationViewModel
import kotlinx.serialization.Serializable


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
            HomePage(

            )
        }

        composable <FavoriteCourseDirection>{
            Text(modifier =  Modifier.fillMaxSize(), text = "Favorite", textAlign = TextAlign.Center)
        }

        composable <AccountPageDirection>{
            Text(modifier =  Modifier.fillMaxSize(), text = "Account", textAlign = TextAlign.Center)
        }
    }
}