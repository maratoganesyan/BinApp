package com.example.binapp

import android.app.Application
import com.example.binapp.data.api.BinListAPIService
import com.example.binapp.di.appModule
import com.example.binapp.di.binListAPIModule
import com.example.binapp.di.databaseModule
import com.example.binapp.di.domainModule
import com.example.binapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BinApp)
            modules(appModule)
        }
    }
}