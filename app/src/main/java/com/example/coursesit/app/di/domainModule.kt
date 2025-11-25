package com.example.coursesit.app.di

import com.example.coursesit.app.viewModel.AuthorizationViewModel
import com.example.coursesit.app.viewModel.MainPageViewModel
import com.example.data.dao.CourseDao
import com.example.data.repository.AuthorizationRepositoryImpl
import com.example.data.repository.FakeAuthorization
import com.example.data.repository.InternetCoursesRepositoryImpl
import com.example.data.repository.LocalCoursesRepositoryImp
import com.example.domain.repository.AuthorizationRepository
import com.example.domain.repository.CoursesRepository
import com.example.domain.useCase.GetCoursesUseCase
import com.example.domain.useCase.SaveAndDeleteUseCase
import com.example.domain.useCase.SignInUseCase
import com.example.domain.useCase.SortCoursesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module{

    viewModelOf(::AuthorizationViewModel)

    factoryOf(::FakeAuthorization)

    single<CoursesRepository>(named("fakeRepository")) { InternetCoursesRepositoryImpl(api = get(named("fakeMainApi")), context = get()) }
    single<CoursesRepository>(named("fakeFavoriteRepository")) { InternetCoursesRepositoryImpl(api = get(named("fakeFavoriteApi")), context = get()) }
    single<CoursesRepository>(named("InternetRepository")) { InternetCoursesRepositoryImpl(api = get(named("real")), context = get()) }

    single<AuthorizationRepository>{ AuthorizationRepositoryImpl(typeSignIn = FakeAuthorization()) }

    factory { GetCoursesUseCase(coursesRepository = get(named("fakeRepository")))}
    factory { SortCoursesUseCase(coursesRepository = get(named("fakeRepository"))) }



    factory (named("getFavoriteUseCase")){ GetCoursesUseCase(coursesRepository = get(named("fakeFavoriteRepository")))}
    factory (named("sortFavoriteUseCase")){ GetCoursesUseCase(coursesRepository = get(named("fakeFavoriteRepository")))}

    factory { SaveAndDeleteUseCase(coursesRepository = LocalCoursesRepositoryImp(course = get())) }

    factory { SignInUseCase(authRep = get()) }


    viewModel <MainPageViewModel> { MainPageViewModel(getCourse = get(), sortCourse = get(), saveAndDeleteUseCase = get()) }
    


    viewModel <AuthorizationViewModel>{ AuthorizationViewModel(authorization = get()) }

    }

