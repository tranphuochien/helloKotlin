package com.example.root.demokotlin.data.local

import com.example.root.demokotlin.data.DataSource
import com.example.root.demokotlin.data.model.Friend
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


/**
 * Created by root on 03/08/2017.
 */

class LocalDataSource @Inject constructor() : DataSource {
    override fun getData(): Observable<ArrayList<Friend>> {
        val list = ArrayList<Friend>()
        list.add( Friend("AaIziyG1JEkBZEuLPbIpL_RKb7yvtCViFtp7wxXQYh_cCVu4z_By2eM5wEGyPk4W_M_E4KUhRLKfaRmWDwd0IjIXsYNMu5AOH5CHtjnb4wNerQ","abc", "https://scontent.xx.fbcdn.net/v/t1.0-1/c8.0.50.50/p50x50/11889657_528231363995171_106694820207113550_n.jpg?oh=68088b2d90424c8d0e745f1b4bab5bad&oe=5A0694D2"));

        return Observable.defer { Observable.just(list) }
    }

}