package com.example.coursesit.app.ui.screen

import com.example.coursesit.R.drawable.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.twotone.Bookmark
import androidx.compose.material.icons.twotone.FilterAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.entity.Course
import com.example.coursesit.app.viewModel.MainPageViewModel
import com.example.coursesit.app.ui.theme.Dark
import com.example.coursesit.app.ui.theme.Green
import com.example.coursesit.app.ui.theme.LightGray
import com.example.coursesit.app.ui.theme.White
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.coursesit.R

import com.example.coursesit.app.ui.theme.DarkGray
import com.example.coursesit.app.ui.theme.Glass
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun HomePage(
    vm: MainPageViewModel = koinViewModel(qualifier = named("HomePage")),
    onCardClick: (Int) -> Unit = {}

){

    HomeBodyPage(
        curses = vm.currentCourses.collectAsState().value,
        onCardClick = onCardClick,
        onLikeClick = {vm.addInFavoriteList(it)},
        onSortDateClick = {vm.sortByTime()}
    )
}

@Composable
fun HomeBodyPage(
    curses: List<Course> = listOf(),
    modifier: Modifier = Modifier,
    onLikeClick: (Int) -> Unit = {},
    onCardClick: (Int) -> Unit = {},
    onSortDateClick:()->Unit
){

    Column(modifier = Modifier.then(modifier).fillMaxSize().padding(horizontal = 16.dp)) {
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

            Box(modifier = Modifier.padding(start =8.dp)
                .width(56.dp).height(56.dp).clip(RoundedCornerShape(28.dp))
                .background(DarkGray)
                .clickable(onClick = {}),
                contentAlignment = Alignment.Center) {
                Icon(
                    modifier = Modifier.requiredSize(24.dp),
                    imageVector = Icons.TwoTone.FilterAlt,
                    contentDescription = "",
                    tint = White
                )
            }

        }
        Box(modifier = Modifier
            .fillMaxWidth()

            .padding(top = 16.dp,end = 16.dp),
            contentAlignment = Alignment.CenterEnd){
            Row(modifier = Modifier.clickable(onClick = onSortDateClick)) {
                Text(
                    text = "По дате добавления",
                    color = Green,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.roboto, FontWeight.W500)),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        letterSpacing = 0.1.sp
                    )
                )
                Icon(
                    modifier = Modifier.requiredSize(16.dp),
                    painter = painterResource(arrow_down_up),
                    contentDescription = "",
                    tint = Green
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 1),
            modifier = Modifier.padding(top = 16.dp).fillMaxSize()
        ) {
            items(curses) {
                CardHomePage(
                    course = it,
                    onLikeClick = onLikeClick,
                    onCardClick = onCardClick
                )
            }

        }
    }

}

@Composable
fun CardHomePage(
    course: Course,
    onCardClick:(Int)->Unit,
    onLikeClick:(Int)->Unit,
){
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .height(236.dp)
            .width(328.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors().copy(
            containerColor = DarkGray,
            contentColor = White
        )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.height(114.dp).fillMaxWidth()
            ) {
                AsyncImage(
                    model = course.image?.toUri(),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(end =8.dp,top = 8.dp),
                    contentAlignment = Alignment.CenterEnd){

                    Box(modifier = Modifier
                        .height(28.dp)
                        .width(28.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Glass)
                        .clickable(onClick = {onLikeClick(course.id)})
                        .padding(6.dp)

                    ){
                        if(course.hasLike == false) {
                            Icon(
                                imageVector = Icons.TwoTone.Bookmark,
                                contentDescription = "",
                                tint = White
                            )
                        }else{
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "",
                                tint = Green
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 8.dp,end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .wrapContentSize().clip(RoundedCornerShape(12.dp))
                        .background(Glass),
                        contentAlignment = Alignment.Center

                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = star),
                                contentDescription = null,
                                alignment = Alignment.TopCenter,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                            Text(
                                text = course.rate.toString(),
                                color = Color.White,
                                modifier = Modifier.padding(end = 6.dp ,start = 4.dp,top = 4.dp, bottom =4.dp),
                            )
                        }
                    }
                    Box(modifier = Modifier.wrapContentSize()
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Glass),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = course.startDate,
                            color = Color.White,
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 6.dp)

                        )

                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize().padding(start = 12.dp,end = 12.dp, top = 16.dp)
            ) {
                Column {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = course.text,
                        style = MaterialTheme.typography.bodySmall,
                        color = White.copy(alpha = 0.7f),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            course.price.toString()+" ₽",
                            style = MaterialTheme.typography.titleMedium,
                            color = White
                        )
                        Row(modifier = Modifier.height(16.dp).clickable(onClick = {}),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                //modifier = Modifier.padding(end = 4.dp),
                                text = "Подробнее",
                                color = Color.Green,
                                style = TextStyle(
                                    fontFamily = FontFamily(Font(R.font.roboto, FontWeight.W600)),
                                    fontSize = 12.sp,
                                    lineHeight = 15.sp,
                                    letterSpacing = 0.4.sp
                                ),
                            )
                            Box(modifier = Modifier.requiredSize(16.dp),
                                contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(arrow_right),
                                    contentDescription = "",
                                    tint = Green,
                                    modifier = Modifier.width(8.dp)
                                )
                            }

                        }
                    }
                }
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
        image = "android.resource://data/src/main/res/drawable/course_1.png"
    ),Course(
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = 999,
        rate = 4.9,
        startDate = "2024-05-22",
        hasLike = false,
        publishDate = "2024-02-02",
        image = "course_2"
    ),Course(
        title = "Java-разработчик с нуля",
        text = "Освойте backend-разработку и программирование на Java, фреймворки Spring и Maven, работу с базами данных и API. Создайте свой собственный проект, собрав портфолио и став востребованным специалистом для любой IT компании.",
        price = 999,
        rate = 4.9,
        startDate = "2024-05-22",
        hasLike = false,
        publishDate = "2024-02-02",
        image = "course_3"
    ))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Dark
    ) {
        HomeBodyPage(
            curses = listCourses,
            modifier = Modifier.padding(top = 40.dp),
            onSortDateClick = {}
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
            image = "course_1"
        ),
        onLikeClick = {},
        onCardClick = {},

    )
}

