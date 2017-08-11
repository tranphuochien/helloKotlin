package com.example.root.demokotlin.presentation.di.modules

import android.app.Activity
import com.example.root.demokotlin.presentation.di.components.activities.FriendListActivityComponent
import com.example.root.demokotlin.presentation.di.components.activities.MainActivityComponent
import com.example.root.demokotlin.presentation.view.activities.FriendListActivity
import com.example.root.demokotlin.presentation.view.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap



/**
 * Created by root on 10/08/2017.
 */
@Module
abstract class ActivitiesBuilder {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivity(builder: MainActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(FriendListActivity::class)
    abstract fun bindFriendListActivity(builder: FriendListActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>
}