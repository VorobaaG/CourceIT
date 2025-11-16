package com.example.coursesit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coursesit.app.screen.authorizationPage
import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.ui.theme.CoursesITTheme
import com.example.coursesit.ui.theme.Dark
import com.example.coursesit.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeBodyViewModel: AuthorizationViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            CoursesITTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    containerColor = Dark,
                    contentColor = White) { innerPadding ->
                    authorizationPage(
                        viewModel = homeBodyViewModel,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesITTheme {
        Greeting("Android")
    }
}