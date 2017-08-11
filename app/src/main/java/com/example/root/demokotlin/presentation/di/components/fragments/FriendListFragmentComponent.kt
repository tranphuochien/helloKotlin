package com.example.root.demokotlin.presentation.di.components

import com.example.root.demokotlin.presentation.di.modules.FriendListFragmentModule
import com.example.root.demokotlin.presentation.view.fragments.FriendListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by root on 10/08/2017.
 */
@Subcomponent(modules = arrayOf(FriendListFragmentModule::class))
interface FriendListFragmentComponent : AndroidInjector<FriendListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<FriendListFragment>()
}