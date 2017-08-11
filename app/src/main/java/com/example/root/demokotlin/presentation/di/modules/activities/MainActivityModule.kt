package com.example.root.demokotlin.presentation.di.modules

import com.example.root.demokotlin.presentation.di.components.fragments.LoginFragmentComponent
import com.example.root.demokotlin.presentation.view.navigation.Navigator
import dagger.Module
import dagger.Provides


/**
 * Created by root on 10/08/2017.
 */
@Module (subcomponents = arrayOf(LoginFragmentComponent::class))
class MainActivityModule {
    @Provides
    fun provideNavigator(): Navigator {
        return Navigator()
    }
}