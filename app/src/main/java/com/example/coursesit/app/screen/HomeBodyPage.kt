package com.example.coursesit.app.screen

import com.example.coursesit.R.drawable.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coursesit.model.Course
import com.example.coursesit.app.viewModel.MainPageViewModel
import com.example.coursesit.ui.theme.Dark
import com.example.coursesit.ui.theme.Green
import com.example.coursesit.ui.theme.LightGray
import com.example.coursesit.ui.theme.White
import java.nio.file.WatchEvent
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    vm: MainPageViewModel = koinViewModel<MainPageViewModel>(),
    onCardClick: (Int) -> Unit = {}

){

    HomeBodyPage(
        curses = vm.currentCourses.collectAsState().value,
        onCardClick = onCardClick,
        onLikeClick = {vm.addInFavoriteList(it)}
    )
}

@Composable
fun HomeBodyPage(
    curses: List<Course> = listOf(),
    modifier: Modifier = Modifier,
    onLikeClick: (Int) -> Unit = {},
    onCardClick: (Int) -> Unit = {}
){

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.then(modifier).fillMaxWidth()
                .height(56.dp)
        ) {
            TextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = LightGray,
                    focusedTextColor = White,
                    unfocusedTextColor = White,
                ),
                maxLines = 1,
                placeholder = {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Search courses...",
                        color = White.copy(alpha = 0.5f),
                        style = MaterialTheme.typography.bodyMedium)
                },
                prefix = {
                    Icon(
                        modifier = Modifier.height(24.dp).width(24.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = White
                    )
                },
                modifier = Modifier.weight(1f).clip(RoundedCornerShape(28.dp)),
                value = "",
                onValueChange = {},
            )
            Button(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(56.dp).height(56.dp)
                    .clip(RoundedCornerShape(28.dp)),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = LightGray
                ),
                onClick = {},
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.FilterAlt,
                    contentDescription = "",
                    tint = White
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 172.dp)
                .fillMaxWidth()
                .clickable(onClick = {}),
            text = "По дате добавления",
            color = Green
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 1),
            modifier = Modifier.padding(top = 16.dp).fillMaxSize()
        ) {
            items(curses) {
                CardHomePage(
                    course = it,
                    onLikeClick = {},
                )
            }

        }
    }

}

@Composable
fun CardHomePage(
    course: Course,
    onCardClick:()->Unit = {},
    onLikeClick:()->Unit={},
){
    Card(
        modifier = Modifier
            .height(236.dp)
            .width(328.dp)
            .padding(bottom = 16.dp),
    ) {
        Column {
            Box(
                modifier = Modifier.height(134.dp).offset(y = (-20).dp).fillMaxWidth(),
                ) {
                Image(
                    painter = painterResource(course.image),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp))
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp,end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .width(46.dp).height(22.dp).clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xFF32333A).copy(alpha = 0.3f))

                    ) {
                        Box(modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 6.dp)
                            .width(34.dp).height(14.dp)

                        ) {
                            Image(
                                painter = painterResource(id = star),
                                contentDescription = null,
                                alignment = Alignment.TopCenter,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.padding(vertical = 1.dp).height(12.dp).width(12.dp)
                            )
                            Text(
                                text = course.rate.toString(),
                                color = Color.White,
                                modifier = Modifier.fillMaxSize().padding(start = 14.dp),
                            )
                        }
                    }
                    Box(modifier = Modifier
                        .width(87.dp).height(22.dp).clip(RoundedCornerShape(12.dp))
                        .background(color = Color(0xFF32333A).copy(alpha = 0.3f))
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                text = course.startDate,
                                color = Color.White,
                                modifier = Modifier.fillMaxSize(),

                            )
                        }
                    }
                }
            }
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp, start = 16.dp,end = 16.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = course.text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(course.price.toString(), style = MaterialTheme.typography.titleLarge, color = Color.Black)
                Text("Подробнее +", color = Color.Green, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true,
    device = "spec:width=360px,height=800px,dpi=160"
)
@Composable
fun PreviewHomePage(){
    val listCourses = listOf(Course(
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = 999,
        rate = 4.9,
        startDate = "2024-05-22",
        hasLike = false,
        publishDate = "2024-02-02",
        image = course_1
    ),Course(
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = 999,
        rate = 4.9,
        startDate = "2024-05-22",
        hasLike = false,
        publishDate = "2024-02-02",
        image = course_2
    ),Course(
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = 999,
        rate = 4.9,
        startDate = "2024-05-22",
        hasLike = false,
        publishDate = "2024-02-02",
        image = course_3
    ))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Dark
    ) {
        HomeBodyPage(
            curses = listCourses,
            modifier = Modifier.padding(top = 40.dp),
            )
    }
}


@Preview
@Composable
fun CardPreviewHomePage(){
    CardHomePage(
        course = Course(
            title = "Java-разработчик с нуля",
            text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
            price = 999,
            rate = 4.9,
            startDate = "2024-05-22",
            hasLike = false,
            publishDate = "2024-02-02",
            image = course_1
        ),
        onLikeClick = {},
    )
}

