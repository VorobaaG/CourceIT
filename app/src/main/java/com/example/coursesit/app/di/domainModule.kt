package com.example.coursesit.app.di

import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.app.viewModel.MainPageViewModel
import com.example.coursesit.data.repository.FakeCoursesApi
import com.example.coursesit.data.repository.InternetCoursesRepositoryImpl
import com.example.coursesit.domain.repository.CoursesRepository
import com.example.coursesit.domain.useCase.GetCoursesUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module{

    viewModelOf(::AuthorizationViewModel)

    single<CoursesRepository>(named("fakeRepository")) { InternetCoursesRepositoryImpl(api = get(named("fake"))) }
    single<CoursesRepository>(named("InternetRepository")) { InternetCoursesRepositoryImpl(api = get(named("real"))) }

    factory { GetCoursesUseCase(coursesRepository = get(named("fakeRepository")))}

    viewModel <MainPageViewModel> { MainPageViewModel(getCourse = get()) }

    }

