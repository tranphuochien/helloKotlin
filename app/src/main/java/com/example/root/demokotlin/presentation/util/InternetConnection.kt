package com.example.root.demokotlin.presentation.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by root on 08/08/2017.
 */
class InternetConnection @Inject
constructor() {
    private var broadcastReceiver: BroadcastReceiver? = null
    private var internetStatusHotObservable: PublishSubject<Boolean> = PublishSubject.create<Boolean>()
    private val maxRetryLimit = 5
    private var delayBetweenRetry = 100
    private var currentRepeatCount = 1


    private fun isInternetOnObservable(): Observable<Boolean> {
        return Observable.fromCallable<Boolean>({ this.isInternetOn() })
    }

    fun isInternetOn(): Boolean {
        val runtime = Runtime.getRuntime()
        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitValue = ipProcess.waitFor()
            return exitValue == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return false
    }

    fun getInternetStatusHotObservable(): Observable<Boolean> {
        return internetStatusHotObservable.serialize()
    }

    /* Register for Internet connection change broadcast receiver */
    fun registerBroadCastReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val extras = intent.extras
                val info = extras.getParcelable<NetworkInfo>("networkInfo")!!
                if (info.state == NetworkInfo.State.CONNECTED) {
                    checkForWorkingInternetConnection()
                } else {
                    internetStatusHotObservable.onNext(isInternetOn())
                }
            }
        }

        context.registerReceiver(broadcastReceiver, filter)
    }

    /* It takes a few milliseconds, from the connection is on
     * to we get an active working internet connection. */
    private fun checkForWorkingInternetConnection() {
        currentRepeatCount = 1
        delayBetweenRetry = 100

        isInternetOnObservable()
                .repeatWhen { objectObservable ->
                    if (currentRepeatCount >= maxRetryLimit) {
                        return@repeatWhen Observable.empty<Boolean>()
                    }
                    currentRepeatCount++
                    delayBetweenRetry += 300
                    Observable.timer(delayBetweenRetry.toLong(), TimeUnit.MILLISECONDS)
                }
                .filter { connectionStatus -> connectionStatus }
                .subscribe { connectionStatus ->
                    currentRepeatCount = maxRetryLimit
                    internetStatusHotObservable.onNext(isInternetOn())
                }
    }

    /* unRegister for Internet connection change broadcast receiver */
    fun unRegisterBroadCastReceiver(context: Context) {
        assert(broadcastReceiver != null)
        context.unregisterReceiver(broadcastReceiver)
    }
}