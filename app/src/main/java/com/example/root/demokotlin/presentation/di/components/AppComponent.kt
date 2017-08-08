package com.example.root.demokotlin.presentation.di.components

import android.content.Context
import android.net.ConnectivityManager
import com.example.root.demokotlin.MyApplication
import com.example.root.demokotlin.data.Repository
import com.example.root.demokotlin.data.RepositoryModule
import com.example.root.demokotlin.data.remote.networking.NetworkModule
import com.example.root.demokotlin.presentation.di.modules.AppModule
import dagger.Component
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Created by root on 03/08/2017.
 */

@Singleton
@Component(
        modules = arrayOf(AppModule::class, RepositoryModule::class, NetworkModule::class)
)
interface AppComponent {
    fun inject(app: MyApplication)

    fun getConnectManager() : ConnectivityManager
    fun getEvenBus(): EventBus
    fun getRepository() : Repository
    fun getContext() : Context
}