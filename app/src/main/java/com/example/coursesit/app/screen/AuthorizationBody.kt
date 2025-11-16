package com.example.coursesit.app.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.ui.theme.CoursesITTheme
import com.example.coursesit.ui.theme.*
import androidx.core.net.toUri


@Composable
fun authorizationPage(
    viewModel: AuthorizationViewModel,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    authorizationBody(
      onPasswordChange = {viewModel.changeValuePassword(it)},
      password = viewModel.getPassword(),
      onLoginChange = {viewModel.changeValueLogin(it)},
      login = viewModel.getLogin(),
      onEnterClick = {},
      onRegistClick = { Log.d("MyTag", "onRegistClick")},
      onForgotPasswordClick = {Log.d("MyTag", "onForgotPasswordClick")},
      statusEnableButton = (viewModel.stateLogin && viewModel.statePassword),
      onOkClick = { context.startActivity(Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())) },
      onVkClick = { context.startActivity(Intent(Intent.ACTION_VIEW, "https://https://vk.com/".toUri()))},
      loginState = viewModel.stateLogin,
      passwordState = viewModel.statePassword
    )
}


@SuppressLint("RememberInComposition")
@Composable
fun authorizationBody(
    onLoginChange:(String)->Unit,
    login:String,
    loginState: Boolean = true,
    onPasswordChange:(String)->Unit ={},
    password:String,
    passwordState: Boolean = true,
    onEnterClick:()-> Unit={},
    statusEnableButton: Boolean,
    onRegistClick:()->Unit={},
    onForgotPasswordClick:()->Unit={},
    onVkClick:()->Unit={},
    onOkClick:()-> Unit={},
    modifier: Modifier = Modifier
){

    val focusRequester2 = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(Modifier.padding(start = 16.dp, end = 16.dp ).fillMaxSize()) {
        Text(
            modifier = Modifier.padding(top = 140.dp).fillMaxWidth(),
            text ="Вход",
            style = MaterialTheme.typography.headlineLarge

        )
        Column(modifier = Modifier.padding(top = 28.dp).fillMaxWidth()) {
            Text(
                text = "Email",
                style = MaterialTheme.typography.titleMedium
            )

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .border(width = 2.dp, color = if(loginState) LightGray else Color.Red, shape = RoundedCornerShape(30.dp))
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = LightGray,
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = login,
                    onValueChange = { onLoginChange(it) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .focusRequester(FocusRequester()),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = White),
                    cursorBrush = SolidColor(White) ,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequester2.requestFocus()
                        }
                    ),

                    singleLine = true,
                    decorationBox = {innerTextField ->
                        if (login.isEmpty()) {
                            Text(
                                "example@gmail.com",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
        Column(modifier = Modifier.padding(top = 16.dp).fillMaxWidth().wrapContentHeight()) {
            Text(
                text = "Пароль",
                style = MaterialTheme.typography.titleMedium
            )
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .border(width = 2.dp, color = if(passwordState) LightGray else Color.Red, shape = RoundedCornerShape(30.dp))
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(
                        color = LightGray,
                        shape = RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = password,
                    onValueChange = { onPasswordChange(it) },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester2),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = White),
                    singleLine = true,
                    cursorBrush = SolidColor(White) ,
                    decorationBox = { innerTextField->
                        if (password.isEmpty()) {
                            Text(
                                "Введите пароль",
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
        Button(
            modifier = Modifier.padding(top = 24.dp).height(40.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White,
                disabledContainerColor = Green,
                disabledContentColor = White),
            onClick = onEnterClick,
            enabled = statusEnableButton
        ){
            Text(
                text = "Вход",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Column{
            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), Arrangement.Center) {
                Text(
                    text = "Нету аккаунта?",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    color = Green,
                    modifier = Modifier.padding(start = 5.dp).clickable(onClick = onRegistClick),
                    text = "Регистрация",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.clickable(onClick = onForgotPasswordClick),
                    text = "Забыл пароль",
                    style = MaterialTheme.typography.titleMedium,
                    color = Green
                )
            }
        }
        Canvas(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
            drawLine(
                color = Stroke,
                start = Offset(0f, 0f),
                end = Offset(328f,0f),
                strokeWidth = 1.dp.toPx()
            )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 32.dp), Arrangement.SpaceEvenly){
            Button(
                modifier = Modifier.width(156.dp).height(40.dp)
                    .background(color = Blue, shape = RoundedCornerShape(30.dp)),
                onClick = onVkClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("VK")
            }
            Button(
                modifier = Modifier.width(156.dp).height(40.dp)
                    .background(
                        brush = Brush.linearGradient(colors = listOf(Color(0xFFF98509), Color(0xFFF95D00))),
                        shape = RoundedCornerShape(30.dp)
                    ),
                onClick = onOkClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
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
            Scaffold(modifier = Modifier.fillMaxSize(),
                containerColor = Dark,
                contentColor = White) { padding->
                authorizationBody(
                    login = "",
                    onLoginChange = {},
                    password = "",
                    onPasswordChange = {},
                    modifier = Modifier.padding(padding),
                    onForgotPasswordClick = {},
                    onRegistClick = {},
                    onEnterClick = {},
                    statusEnableButton = false,
                    onVkClick = {},
                    onOkClick = {}
                )
            }
        }

}