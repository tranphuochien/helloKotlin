package com.example.root.demokotlin.presentation.view.activities

import android.app.Activity
import android.app.Fragment
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import com.example.root.demokotlin.MyApplication
import com.example.root.demokotlin.data.model.MessageEvent
import com.example.root.demokotlin.presentation.di.components.ActivityComponent
import com.example.root.demokotlin.presentation.di.components.AppComponent
import com.example.root.demokotlin.presentation.di.components.DaggerActivityComponent
import com.example.root.demokotlin.presentation.di.modules.ActivityModule
import com.example.root.demokotlin.presentation.util.NetworkManagement
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by root on 03/08/2017.
 */
abstract class BaseActivity : Activity() {
    lateinit var activityComponent : ActivityComponent
    lateinit var evenBus : EventBus
    private var receiver : BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val appComponent : AppComponent = (application as MyApplication).appComponent
        activityComponent = DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .appComponent(appComponent)
                .build()

        super.onCreate(savedInstanceState)

        evenBus  = appComponent.getEvenBus()
        receiver = NetworkManagement(appComponent)

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        onInject()
    }

    abstract fun onInject()

    override fun onResume() {
        super.onResume()
        evenBus.register(this)
    }

    override fun onPause() {
        super.onPause()
        evenBus.unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        //activityComponent = null
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onEvent(messageEvent: MessageEvent) {
        //Handle event
        showToastMessage(messageEvent.message)

        //Remove current sticky event
        evenBus.removeStickyEvent(MessageEvent::class.java)
    }

    fun showToastMessage(message: String?) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }
}