package com.example.root.demokotlin.presentation.di.components

import com.example.root.demokotlin.presentation.di.modules.FragmentsBuilder
import com.example.root.demokotlin.presentation.di.modules.MainActivityModule
import com.example.root.demokotlin.presentation.view.activities.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector



/**
 * Created by root on 10/08/2017.
 */

@Subcomponent(modules = arrayOf(
        MainActivityModule::class,
        FragmentsBuilder::class
))
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}