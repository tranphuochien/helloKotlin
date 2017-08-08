package com.example.root.demokotlin.presentation.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.root.demokotlin.data.model.MessageEvent
import com.example.root.demokotlin.presentation.di.components.AppComponent


/**
 * Created by root on 03/08/2017.
 */

class NetworkManagement(val appComponent: AppComponent) : BroadcastReceiver() {
    companion object {
        fun isNetworkAvailable(appComponent: AppComponent) : Boolean {
            val netInfo = appComponent.getConnectManager().activeNetworkInfo
            return netInfo != null && netInfo.isConnected()
        }
    }
    override fun onReceive(p0: Context?, p1: Intent?) {
        val status : Boolean = isNetworkAvailable(appComponent)
        if (status) {
            appComponent.getEvenBus().postSticky(MessageEvent("Network Available"))
        } else {
            appComponent.getEvenBus().postSticky(MessageEvent("Fail. Oops!"))
        }

    }
}