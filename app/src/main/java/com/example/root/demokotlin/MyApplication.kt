package com.example.root.demokotlin

import android.app.Application
import com.example.root.demokotlin.data.RepositoryModule
import com.example.root.demokotlin.data.remote.networking.NetworkModule
import com.example.root.demokotlin.presentation.di.components.AppComponent
import com.example.root.demokotlin.presentation.di.components.DaggerAppComponent
import com.example.root.demokotlin.presentation.di.modules.AppModule

/**
 * Created by root on 03/08/2017.
 */
class MyApplication : Application() {
    lateinit var appComponent : AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .repositoryModule(RepositoryModule())
                .build()
    }
}