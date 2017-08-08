package com.example.root.demokotlin.data

import com.example.root.demokotlin.data.local.LocalDataSource
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.data.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Predicate
import java.util.*
import javax.inject.Inject

/**
 * Created by root on 03/08/2017.
 */

class Repository @Inject
constructor(remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource) : DataSource {

    private val mRemoteDataSource: DataSource
    private val mLocalDataSource: DataSource
    private var mObservableLocal: Observable<ArrayList<Friend>>? = null
    private var mObservableRemote: Observable<ArrayList<Friend>>? = null

    init {
        this.mRemoteDataSource = remoteDataSource
        this.mLocalDataSource = localDataSource
    }

    override fun getData() : Observable<ArrayList<Friend>>{
        this.mObservableLocal = mLocalDataSource.getData()
        this.mObservableRemote = mRemoteDataSource.getData()

        return Observable
                .concat<ArrayList<Friend>>(mObservableRemote!!, this.mObservableLocal!!)
                .filter(object : Predicate<ArrayList<Friend>> {
                    @Throws(Exception::class)
                    override fun test(@NonNull friends: ArrayList<Friend>): Boolean {
                        return true
                    }
                })
                .take(1)
    }

}
