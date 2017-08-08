package com.example.root.demokotlin.data

import com.example.root.demokotlin.data.model.Friend
import io.reactivex.Observable
import java.util.*

/**
 * Created by root on 03/08/2017.
 */
interface DataSource {
    abstract fun getData(): Observable<ArrayList<Friend>>
}