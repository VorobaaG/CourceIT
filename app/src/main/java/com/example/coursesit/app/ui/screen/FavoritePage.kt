package com.example.coursesit.app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.coursesit.app.viewModel.MainPageViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun FavoritePage(
    vm: MainPageViewModel = koinViewModel(qualifier = named("FavoritePage")),
    onCardClick: (Int) -> Unit = {})
{
    HomeBodyPage(
        curses = vm.currentCourses.collectAsState().value,
        onCardClick = onCardClick,
        onLikeClick = {vm.addInFavoriteList(it)},
        onSortDateClick = {vm.sortByTime()}
    )

}