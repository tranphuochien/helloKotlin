package com.example.root.demokotlin.presentation.view.navigation

import android.content.Context
import com.example.root.demokotlin.presentation.view.activities.FriendListActivity
import javax.inject.Inject


/**
 * Created by root on 03/08/2017.
 */
class Navigator {
    @Inject
    constructor(){
    }

    fun navigateToFriendList(context : Context) {
        val intentToLaunch = FriendListActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
    }
}