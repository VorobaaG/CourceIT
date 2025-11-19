package com.example.coursesit.app.ui

import android.R.attr.visible
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursesit.ui.theme.DarkGray
import com.example.coursesit.ui.theme.Green
import com.example.coursesit.ui.theme.Stroke
import com.example.coursesit.ui.theme.White

enum class BottomNavigationState{
    HOME,
    FAVORITE,
    ACCOUNT
}

@Composable
fun CourseBottomAppBar(
    modifier: Modifier = Modifier,
    currentBottomState:BottomNavigationState = BottomNavigationState.HOME,
    onClick:(BottomNavigationState)->Unit ={}
) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(DarkGray),
            contentAlignment = Alignment.Center

        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(32.dp).width(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(onClick = { onClick(BottomNavigationState.HOME) })
                            .background(if (currentBottomState == BottomNavigationState.HOME) Stroke else DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.width(24.dp).height(24.dp),
                            imageVector = Icons.Default.House,
                            tint = if (currentBottomState == BottomNavigationState.HOME) Green else White,
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentBottomState == BottomNavigationState.HOME) Green else White,
                        text = "Главная"
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(32.dp).width(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(onClick = { onClick(BottomNavigationState.FAVORITE) })
                            .background(if (currentBottomState == BottomNavigationState.FAVORITE) Stroke else DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.width(24.dp).height(24.dp),
                            imageVector = Icons.Default.Bookmark,
                            tint = if (currentBottomState == BottomNavigationState.FAVORITE) Green else White,
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentBottomState == BottomNavigationState.FAVORITE) Green else White,
                        text = "Избранное"
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier.height(32.dp).width(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable(onClick = { onClick(BottomNavigationState.ACCOUNT) })
                            .background(if (currentBottomState == BottomNavigationState.ACCOUNT) Stroke else DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.width(24.dp).height(24.dp),
                            imageVector = Icons.Default.Person,
                            tint = if (currentBottomState == BottomNavigationState.ACCOUNT) Green else White,
                            contentDescription = ""
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentBottomState == BottomNavigationState.ACCOUNT) Green else White,
                        text = "Аккаунт"
                    )
                }
            }
        }

}

@Preview
@Composable
fun PreviewCourseBottomAppBar(){
    CourseBottomAppBar()
}