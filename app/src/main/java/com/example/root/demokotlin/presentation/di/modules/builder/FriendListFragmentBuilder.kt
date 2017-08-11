package com.example.root.demokotlin.presentation.di.modules.builder

import android.app.Fragment
import com.example.root.demokotlin.presentation.di.components.fragments.FriendListFragmentComponent
import com.example.root.demokotlin.presentation.view.fragments.FriendListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.FragmentKey
import dagger.multibindings.IntoMap

/**
 * Created by root on 11/08/2017.
 */
@Module
abstract class FriendListBuilder {

    @Binds
    @IntoMap
    @FragmentKey(FriendListFragment::class)
    abstract fun bindFriendListFragment(builder: FriendListFragmentComponent.Builder):
            AndroidInjector.Factory<out Fragment>
}