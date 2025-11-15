package com.example.coursesit.app.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.coursesit.ui.theme.CoursesITTheme
import com.example.coursesit.ui.theme.*

@Composable
fun authorizationPage(
    modifier: Modifier = Modifier
){
    Column(Modifier.padding(start = 16.dp, end = 16.dp ).fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 140.dp).fillMaxWidth(),
            text ="Вход",
            style = MaterialTheme.typography.headlineLarge

        )
        Column(modifier = Modifier.padding(top = 64.dp).fillMaxWidth()) {
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleMedium
            )
            TextField(
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                placeholder = {
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = "example@gmail.com",
                    )
                },
                onValueChange = {},
                value = "",
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = LightGray,
                    focusedContainerColor = LightGray,
                    unfocusedTextColor = White,
                    focusedTextColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp)
            )
        }
        Column(modifier = Modifier.padding(top = 16.dp).fillMaxWidth().wrapContentHeight()) {
            Text(
                text = "Пароль",
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                placeholder = {
                    Text(
                        text = "Введите пароль",
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
                onValueChange = {},
                value = "",
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = LightGray,
                    focusedContainerColor = LightGray,
                    unfocusedTextColor = White,
                    focusedTextColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp)
            )
        }
        Button(
            modifier = Modifier.padding(top = 24.dp).height(40.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White),
            onClick = {}) {
            Text(
                text = "Вход",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Column{
            Row(modifier = Modifier.fillMaxWidth().padding(top = 5.dp), Arrangement.Center) {
                Text(
                    text = "Нету аккаунта?",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    color = Green,
                    modifier = Modifier.padding(start = 5.dp).clickable(onClick = {}),
                    text = "Регитрация",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable(onClick = {}),
                    text = "Забыл пароль",
                    style = MaterialTheme.typography.titleMedium,
                    color = Green
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 5.dp), Arrangement.SpaceEvenly){
            Button(
                modifier = Modifier.width(156.dp).height(40.dp),
                onClick = {},
                colors = ButtonColors(
                    contentColor = White,
                    containerColor = Blue,
                    disabledContentColor = White,
                    disabledContainerColor = Blue)
            ) {
                Text("VK")
            }
            Button(
                modifier = Modifier.width(156.dp).height(40.dp),
                onClick = {}
            ) {
                Text("OK")
            }
        }
    }
}



@Preview(showSystemUi = true, showBackground = true,
    device = "spec:width=360px,height=800px,dpi=120",
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.NONE
)
@Composable
fun previewPage(){

        CoursesITTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { padding->
                authorizationPage(
                    modifier = Modifier.padding(padding)
                )
            }
        }

}