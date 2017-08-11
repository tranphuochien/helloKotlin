package com.example.root.demokotlin.presentation.di.modules

import com.example.root.demokotlin.presentation.presenter.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by root on 10/08/2017.
 */

@Module
class LoginFragmentModule {
    @Provides
    fun provideLoginPresenter(): LoginPresenter {
        return LoginPresenter()
    }
}