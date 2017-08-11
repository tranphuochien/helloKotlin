package com.example.root.demokotlin.presentation.di.components

import com.example.root.demokotlin.presentation.di.modules.LoginFragmentModule
import com.example.root.demokotlin.presentation.view.fragments.LoginFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by root on 10/08/2017.
 */
@Subcomponent(modules = arrayOf(LoginFragmentModule::class))
interface LoginFragmentComponent : AndroidInjector<LoginFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<LoginFragment>()
}