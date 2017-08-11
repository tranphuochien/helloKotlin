package com.example.root.demokotlin.presentation.di.modules

import com.example.root.demokotlin.presentation.di.components.fragments.FriendListFragmentComponent
import dagger.Module

/**
 * Created by root on 10/08/2017.
 */
@Module (subcomponents = arrayOf(FriendListFragmentComponent::class))
class FriendListActivityModule{
}