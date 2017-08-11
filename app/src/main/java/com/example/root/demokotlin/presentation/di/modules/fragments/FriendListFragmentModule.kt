package com.example.root.demokotlin.presentation.di.modules

import android.content.Context
import com.example.root.demokotlin.presentation.presenter.FriendListPresenter
import com.example.root.demokotlin.presentation.util.ImageManager.ImageManager
import com.example.root.demokotlin.presentation.util.InternetConnection
import com.example.root.demokotlin.presentation.view.adapter.FriendsAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by root on 10/08/2017.
 */
@Module
class FriendListFragmentModule {
    @Provides

    fun provideFriendListPresenter(internetConnection: InternetConnection): FriendListPresenter {
        return FriendListPresenter(internetConnection)
    }

    @Provides
    fun provideFriendsAdapter(context : Context, imageManager : ImageManager) : FriendsAdapter {
        return FriendsAdapter(context, imageManager)
    }
}