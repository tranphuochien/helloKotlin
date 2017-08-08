package com.example.root.demokotlin.presentation.di.components

import com.example.root.demokotlin.presentation.di.ForFragment
import com.example.root.demokotlin.presentation.di.modules.ActivityModule
import com.example.root.demokotlin.presentation.view.activities.MainActivity
import com.example.root.demokotlin.presentation.view.fragments.FriendListFragment
import com.example.root.demokotlin.presentation.view.fragments.LoginFragment
import dagger.Component


/**
 * Created by root on 03/08/2017.
 */

@ForFragment
@Component(
        modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class)
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(friendListFragment: FriendListFragment)
    fun inject(loginFragment: LoginFragment)
}