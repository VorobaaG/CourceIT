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

    single<CoursesRepository>(named("FakeRepository")) { InternetCoursesRepositoryImpl(api = get(named("fakeMainApi")), context = get()) }
    single<CoursesRepository>(named("InternetRepository")) { InternetCoursesRepositoryImpl(api = get(named("real")), context = get()) }
    single<CoursesRepository>(named("LocalRepository")){ LocalCoursesRepositoryImp(course = get()) }

    single<AuthorizationRepository>{ AuthorizationRepositoryImpl(typeSignIn = FakeAuthorization()) }

    factory<GetCoursesUseCase> (named("getFakeCoursesUserCase")){ GetCoursesUseCase(coursesRepository = get(named("FakeRepository")))}
    factory<GetCoursesUseCase> (named("getLocalCoursesUserCase")){ GetCoursesUseCase(coursesRepository = get((named("LocalRepository")))) }


    factory<SortCoursesUseCase> (named("sortFakeCoursesUserCase")){ SortCoursesUseCase(coursesRepository = get(named("FakeRepository"))) }
    factory<SortCoursesUseCase> (named("sortLocalCoursesUserCase")){ SortCoursesUseCase(coursesRepository = get(named("LocalRepository"))) }

    factory<SaveAndDeleteUseCase>{ SaveAndDeleteUseCase(coursesRepository = LocalCoursesRepositoryImp(course = get())) }

    factory<SignInUseCase>{ SignInUseCase(authRep = get()) }


    viewModel <MainPageViewModel> (named("HomePage")){
        MainPageViewModel(getCourse = get(named("getFakeCoursesUserCase")), sortCourse = get(named("sortFakeCoursesUserCase")), saveAndDeleteUseCase = get()) }
    viewModel <MainPageViewModel> (named("FavoritePage")){
        MainPageViewModel(getCourse = get(named("getLocalCoursesUserCase")), sortCourse = get(named("sortLocalCoursesUserCase")), saveAndDeleteUseCase = get()) }


    viewModel <AuthorizationViewModel>{ AuthorizationViewModel(authorization = get()) }

    }

