package com.example.root.demokotlin.data.remote

import com.example.root.demokotlin.data.DataSource
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.data.remote.networking.Service
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


/**
 * Created by root on 03/08/2017.
 */
class RemoteDataSource @Inject
constructor(private val service: Service) : DataSource {
    override fun getData(): Observable<ArrayList<Friend>> {
        return service.mFacebookAPI()
    }
}
