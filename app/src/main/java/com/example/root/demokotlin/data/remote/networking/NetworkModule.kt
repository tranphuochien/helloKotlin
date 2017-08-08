package com.example.root.demokotlin.data.remote.networking

import android.util.Log
import com.example.root.demokotlin.presentation.di.components.AppComponent
import com.facebook.FacebookSdk.getCacheDir
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

/**
 * Created by root on 03/08/2017.
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideCall(cacheFile: File, appComponent: AppComponent): Retrofit {
        var cache: Cache? = null

        try {
            cache = Cache(cacheFile, (10 * 1024 * 1024).toLong())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val okHttpClient = OkHttpClient.Builder()
                //.cache(cache)
                //.addNetworkInterceptor(FeedInterceptor.getOnlineInterceptor())
                //.addInterceptor(FeedInterceptor.getOfflineInterceptor(appComponent))
                .build()

        return Retrofit.Builder()
                .baseUrl("https://graph.facebook.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesService(networkService: NetworkService): Service {
        return Service(networkService)
    }

    @Provides
    @Singleton
    fun providesCacheFile(): File {
        Log.d("Debug", "Create Cache")
        return File(getCacheDir(), "responses")
    }
}