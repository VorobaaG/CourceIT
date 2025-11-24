package com.example.coursesit.app.di

import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.app.viewModel.MainPageViewModel
import com.example.data.repository.AuthorizationRepositoryImpl
import com.example.data.repository.FakeAuthorization
import com.example.data.repository.InternetCoursesRepositoryImpl
import com.example.domain.repository.AuthorizationRepository
import com.example.domain.repository.CoursesRepository
import com.example.domain.useCase.GetCoursesUseCase
import com.example.domain.useCase.SignInUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module{

    viewModelOf(::AuthorizationViewModel)

    factoryOf(::FakeAuthorization)

    single<CoursesRepository>(named("fakeRepository")) { InternetCoursesRepositoryImpl(api = get(named("fake")), context = get()) }
    single<CoursesRepository>(named("InternetRepository")) { InternetCoursesRepositoryImpl(api = get(named("real")), context = get()) }

    single<AuthorizationRepository>{ AuthorizationRepositoryImpl(typeSignIn = FakeAuthorization()) }

    factory { GetCoursesUseCase(coursesRepository = get(named("fakeRepository")))}
    factory { SignInUseCase(authRep = get()) }

    viewModel <MainPageViewModel> { MainPageViewModel(getCourse = get()) }

    viewModel <AuthorizationViewModel>{ AuthorizationViewModel(authorization = get()) }

    }

