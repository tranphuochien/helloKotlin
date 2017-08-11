package com.example.root.demokotlin.presentation.di.modules.builder

import android.app.Fragment
import com.example.root.demokotlin.presentation.di.components.fragments.LoginFragmentComponent
import com.example.root.demokotlin.presentation.view.fragments.LoginFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.FragmentKey
import dagger.multibindings.IntoMap


/**
 * Created by root on 10/08/2017.
 */

@Module
abstract class FragmentsBuilder {
    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    abstract fun bindLoginFragment(builder: LoginFragmentComponent.Builder):
            AndroidInjector.Factory< out Fragment>
}