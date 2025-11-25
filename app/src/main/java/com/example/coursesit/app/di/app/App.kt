package com.example.coursesit.app.di.app

import android.app.Application
import com.example.coursesit.app.di.domainModule
import com.example.coursesit.app.di.networkModule
import com.example.coursesit.app.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(domainModule, networkModule, roomModule)
        }

    }
}