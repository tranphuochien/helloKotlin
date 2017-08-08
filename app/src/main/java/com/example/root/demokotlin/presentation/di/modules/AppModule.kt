package com.example.root.demokotlin.presentation.di.modules

import android.content.Context
import android.net.ConnectivityManager
import com.example.root.demokotlin.MyApplication
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

/**
 * Created by root on 03/08/2017.
 */
@Module
class AppModule(val app : MyApplication){
    @Provides
    @Singleton
    fun provideApplication(): MyApplication {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideConnectivityManager() : ConnectivityManager {
        return  app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideEvenBus() : EventBus {
        return EventBus.getDefault()
    }


}