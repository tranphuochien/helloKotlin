package com.example.root.demokotlin.data

import com.example.root.demokotlin.data.local.LocalDataSource
import com.example.root.demokotlin.data.remote.RemoteDataSource
import com.example.root.demokotlin.data.remote.networking.Service
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by root on 03/08/2017.
 */
@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideRemoteDataSource(service: Service): RemoteDataSource {
        return RemoteDataSource(service)
    }

    @Provides
    @Singleton
    internal fun provideLocalDataSource(service: Service): LocalDataSource {
        return LocalDataSource()
    }


}