package com.example.root.demokotlin.presentation.di.components

import com.example.root.demokotlin.presentation.di.modules.FriendListActivityModule
import com.example.root.demokotlin.presentation.di.modules.FriendListBuilder
import com.example.root.demokotlin.presentation.view.activities.FriendListActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by root on 10/08/2017.
 */
@Subcomponent(modules = arrayOf(
        FriendListActivityModule::class,
        FriendListBuilder::class
))
interface FriendListActivityComponent : AndroidInjector<FriendListActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FriendListActivity>()
}